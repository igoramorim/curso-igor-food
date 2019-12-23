package testes.spark;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.expressions.AssertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mycompany.igorfood.testes.spark.SparkConstants;
import com.mycompany.igorfood.testes.spark.SparkFile;
import com.mycompany.igorfood.testes.spark.SparkFileCSV;
import com.mycompany.igorfood.testes.spark.SparkFileJSON;
import com.mycompany.igorfood.testes.spark.SparkModel;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class SparkMainTest {
	
	private static final int RECORD_COUNT = 1000;
	
	@Spy
	SparkFileJSON sparkFile;
	
	@Test
	public void returnSuccessfulWhenFileIsCreatedTest() {
		SparkSession spark = getSparkSession();
		Dataset<Row> dataDF = getData(spark);
		
		String outputPath = getOutputPath();
		sparkFile.generateFile(spark, dataDF, outputPath);
		
		File file = new File(outputPath);
		assertTrue(file.exists());
		
		file.delete();
		
		spark.close();
	}
	
	private SparkSession getSparkSession() {
		SparkSession spark = SparkSession.builder()
    			.appName("teste spark")
    			.master("local[*]")
    			.config("spark.network.timeout", "600s")
    			.getOrCreate();
		
		return spark;
	}
	
	private Dataset<Row> getData(SparkSession spark) {
		SparkModel obj = new SparkModel();
		
		List<SparkModel> list = new ArrayList<SparkModel>(RECORD_COUNT);
	    for (int i = 0; i < RECORD_COUNT; i++) {
	        list.add(obj);
	    }
	    
	    Dataset<Row> listDF = spark.createDataFrame(list, SparkModel.class);
	    return listDF;
	}

	private String getOutputPath() {
		return "src/test/resources/spark-data/file".concat(SparkConstants.FILE_GZIP_EXT);
	}
}
