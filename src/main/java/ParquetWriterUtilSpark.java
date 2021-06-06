import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ParquetWriterUtilSpark {

    public static void main (String args[]) throws IOException {

        String jsonFilePath = getJsonFilePath();

        SparkConf conf= new SparkConf().setAppName("Java Spark").setMaster("local[*]");

        SparkSession spark = SparkSession
                .builder()
                .config(conf)
                .getOrCreate();

        Dataset<Row> peopleDF = spark.read().json(jsonFilePath);

        peopleDF.write().mode(SaveMode.Overwrite).parquet("SampleOutput.parquet");
    }

    public static String getJsonFilePath() throws IOException {
        PNPhase2Data.AlgoScore algo1 = PNPhase2Data.AlgoScore.builder()
                .algo("algo1")
                .scoreValue(0.9)
                .build();
        PNPhase2Data.CatalogData catalogData = PNPhase2Data.CatalogData.builder()
                .catalogId(456)
                .rank(1)
                .notificationTimestamp("1234t56778")
                .scores(Arrays.asList(algo1))
                .build();
        PNPhase2Data pnPhase2Data = PNPhase2Data.builder()
                .userId(12345)
                .catalogDataList(Arrays.asList(catalogData))
                .build();


        //creating jsonFile from jsonString , since there is no method from spark lib to read json String directly
        String json2= new ObjectMapper().writeValueAsString(pnPhase2Data);
        String jsonFilePath = "jsonInput.json";
        FileWriter file = new FileWriter(jsonFilePath);
        file.write(json2);
        file.flush();

        return jsonFilePath;
    }


}

