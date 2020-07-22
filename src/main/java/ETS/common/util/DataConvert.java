package ETS.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class DataConvert {

	@SuppressWarnings({ "unchecked" })
	public static String convertChartData(List<DataMap> dataList, String key, String[] label, String[] dataKey, String[] color) 
			throws JsonGenerationException, JsonMappingException, IOException {

		DataMap chart = new DataMap();

		{// 1. labels 데이터 생성
			ArrayList<String> labels = new ArrayList<String>();
			for(int i=0; i<dataList.size(); i++){
				labels.add(dataList.get(i).get(key).toString());
			}
			chart.put("labels", labels);
		}

		{// 2.datasets 데이터 생성
			ArrayList<DataMap> datasets = new ArrayList<DataMap>();

			DataMap chartRowData = null;
			ArrayList<String> data = null;
			
			for (int i = 0; i < label.length; i++) {
				chartRowData = new DataMap();
				data = new ArrayList<String>();
				
				chartRowData.put("label", label[i]);
				
				for (int j = 0; j < dataList.size(); j++) {
					data.add(dataList.get(j).getString(dataKey[i]));
				}
				
				chartRowData.put("data", data);
				chartRowData.put("color", color[i]);

				datasets.add(chartRowData);
			}
			chart.put("datasets", datasets);
		}

		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(chart);
	}
}
