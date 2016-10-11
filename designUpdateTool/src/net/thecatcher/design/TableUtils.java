package net.thecatcher.design;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;




public class TableUtils {
	
	private static Map<String, SectorBean> designTable = new HashMap<>();
	
	public static final File fileLocation = TableUtils.getLastestDesignFile();
	

	/* read the design file 
	 * fill in a Map and return it.
	 */
	public static Map<String, SectorBean> readTable() throws IOException, ParseException{
		String line ="";
		BufferedReader br = new BufferedReader(new FileReader(fileLocation));
		while((line=br.readLine())!=null){
			String[] lineInfo = line.split("\\s+");
			//throw exception if sector name in a illegal fromat
			if(!(lineInfo[0].matches("\\S+\\d$"))){
				throw new ParseException();
			}
			
			//new a sector
			SectorBean sector = new SectorBean(lineInfo[0],lineInfo[1],lineInfo[2]);
			designTable.put(lineInfo[0], sector);
			
		}
		return designTable;
	}
	
	/*
	 * write back to file
	 */
	public static void writeBack() throws IOException{
		StringBuilder table = new StringBuilder();
		String workDirectory = System.getProperty("user.dir");
//		System.out.println(workDirectory);
		
		Date currentDate = new Date();
		DateFormat currentSimpleTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = currentSimpleTime.format(currentDate);
		String fileName = "design"+currentTime+".csv";
		File updateDesignFile = new File(workDirectory, fileName);
		FileWriter writer = new FileWriter(updateDesignFile);
		for(String eachkey:designTable.keySet()){
			SectorBean eachSector = designTable.get(eachkey);
			StringBuilder line = new StringBuilder();
			line.append(eachSector.getSectorName()).append(" ");
			line.append(eachSector.getPci()).append(" ");
			line.append(eachSector.getPsn()).append("\n");
			table.append(line);
		}
		writer.write(table.toString());
		writer.close();
		
	}
	public static File getLastestDesignFile(){
		String workingDirectory = System.getProperty("user.dir");
		File dir = new File(workingDirectory);
		File[] files = dir.listFiles();
		List<String> fileList = new LinkedList<>();
		String filename=null;
		for(int i=0;i<files.length;i++){
			filename=files[i].getName();
			if(filename.matches("design.*")){
				fileList.add(filename);
			}
		}
		String lastestFileName = Collections.max(fileList);
		

		
		File lastedDesignFile = new File(workingDirectory, lastestFileName);
		
		return lastedDesignFile;
	}
	

	public static void modifyTable(List<SectorBean> SectorList){
			int modifyCounter =0;
			int addedCounter =0;
			Set keySet = designTable.keySet();
			for(int i =0;i<SectorList.size();i++){
				boolean hasSameName = keySet.contains(SectorList.get(i).getSectorName());
				
				if(hasSameName){
					boolean isSameSector = designTable.get(SectorList.get(i).getSectorName()).equals(SectorList.get(i));
					if(!isSameSector)
						modifyCounter++;
				}else {
					
					addedCounter++;
				}
				designTable.put(SectorList.get(i).getSectorName(), SectorList.get(i));
			}
			System.out.println("result:");
			System.out.println("--------------------------------------------------------------");
			System.out.println("modify: "+modifyCounter+" rercords");
			System.out.println("add "+addedCounter+" records");
	}
	

}
