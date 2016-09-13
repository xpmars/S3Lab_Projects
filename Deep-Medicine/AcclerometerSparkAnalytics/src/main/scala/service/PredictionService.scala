package service

import java.util.Date
import com.datastax.spark.connector.rdd.CassandraRDD
import com.datastax.spark.connector.{CassandraRow, SparkContextFunctions}
import model.PredictionResult
import org.apache.spark.SparkConf
import org.apache.spark.mllib.tree.model.RandomForestModel
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Durations, StreamingContext}

object Spark{
  val sparkConf: SparkConf = new SparkConf().setAppName("User's physical activity recognition").set("spark.cassandra.connection.host", "127.0.0.1").setMaster("local[*]")
  val ssc = new StreamingContext(sparkConf, Durations.seconds(5))
}

object PredictionService{
    val RANDOM_FOREST_PREDICTION_MODEL: String = "training_acceleration_3"
    val ACCELERATION_TOTAL: Long = 100l
    val KEYSPACE: String = "activityrecognition"
    val RESULT_TABLE: String = "result"
    val TEST_USER: String = "TEST_USER"
    val ACCELERATION_TABLE: String = "acceleration"

    def main(args: Array[String]) {
      predictWithRealTimeStreaming()
    }

    private def predictWithRealTimeStreaming() {

      val cassandraRowsRDD: CassandraRDD[CassandraRow] = new SparkContextFunctions(Spark.ssc.sparkContext).cassandraTable(KEYSPACE, ACCELERATION_TABLE)
      val model: RandomForestModel = RandomForestModel.load(Spark.ssc.sparkContext, RANDOM_FOREST_PREDICTION_MODEL)

      val cassandraReceiver: ReceiverInputDStream[RDD[CassandraRow]] = Spark.ssc.receiverStream(new CassandraReceiver(StorageLevel.MEMORY_ONLY))
      cassandraReceiver.map(rdd => computePrediction(model, rdd)).print()

      Spark.ssc.start
      Spark.ssc.awaitTermination
    }

  def computePrediction(model: RandomForestModel, rdd: RDD[CassandraRow]): Unit = {
    println("****************************** start")
    val predict: String = FeaturesService.predict(model, FeaturesService.computeFeatures(rdd))
    val predictions: List[CassandraRow] = List(CassandraRow.fromMap(predictionResultToMap(new PredictionResult(TEST_USER, new Date().getTime, predict))))
    val result: RDD[CassandraRow] = Spark.ssc.sparkContext.parallelize(predictions)
    result.saveToCassandra(KEYSPACE, "result")
    println("****************************  Predicted activity = " + predict)

  }

  def predictionResultToMap(prediction: PredictionResult): Map[String, Any] = {
    val fieldNames = prediction.getClass.getDeclaredFields.map(_.getName)
    val vals = PredictionResult.unapply(prediction).get.productIterator.toSeq
    fieldNames.zip(vals).toMap
  }

}

