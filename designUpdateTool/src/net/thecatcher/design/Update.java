package net.thecatcher.design;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;





import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class Update {
	private File designFileLocation;
	private File updateFileLocation;

	public Update(File designFileLocation, File updateFileLocation) {
		this.designFileLocation = designFileLocation;
		this.updateFileLocation = updateFileLocation;
	}

	/*
	 * parse each line in update.csv return a list about the sectors waiting for
	 * update
	 */
	private List<SectorBean> getUpdateSector() throws ParseException {
		String[] UpdateSectorInfo = null;
		String line = null;
		List<SectorBean> UpdateSectors = new LinkedList<>();
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(updateFileLocation));
			while ((line = br.readLine()) != null) {
				UpdateSectorInfo = line.split("\\s+");
				//==========================================================================================
				//removed by tina's request
				//------------------------------------------------------------------------------------------
				//if (!(UpdateSectorInfo[0].matches("\\S+\\d$"))) {
				//throw new ParseException();
				//}
				//==========================================================================================
				UpdateSectors.add(new SectorBean(UpdateSectorInfo[0], UpdateSectorInfo[1], UpdateSectorInfo[2]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		return UpdateSectors;
	}

	public static void main(String[] args) throws ParseException, IOException {
		//get the update file
		String workDirectory = System.getProperty("user.dir");
		String updateFileName = "update.txt";
		File updateFile = new File(workDirectory, updateFileName);
		//get the lastest designFile 
		File designFile = TableUtils.fileLocation;

		System.out.println("====================================================================");
		System.out.println("the lastest designfile is: "+designFile.getName());
		System.out.println("--------------------------------------------------------------------");
		System.out.println("please choise the way to writeback:");
		System.out.println("(1)write to a new file");
		System.out.println("(2)write back to the previous file");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("I strongly recommend to write to a NEW FILE \n"+
							"Because I don't believe my program can alawys get the right result...");
		System.out.println("--------------------------------------------------------------------");
		System.out.print("pelase enter the choice: ");
		Scanner in=new Scanner(System.in);
		String choice = in.nextLine();
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		System.out.println("now I need parse the design file...");
		
		Update update = new Update(designFile, updateFile);

		List<SectorBean> updateSectors = update.getUpdateSector();

		Map designMap = null;
		try {
			designMap = TableUtils.readTable();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------------------------------");
		TableUtils.modifyTable(updateSectors);
		System.out.println("--------------------------------------------------------------------");
		try {
			if(choice.equals("1")){
				String updatedFileName = TableUtils.writeToaNewFile();
				System.out.println("the result is stored in "+updatedFileName);
				
			}else if(choice.equals("2")){
				TableUtils.writeBack();
				System.out.println("The result is stored in "+TableUtils.fileLocation.getName());
			}else{
				System.out.println("If you can see me,the program must crazy");
				System.out.println("I don't kown what happend...maybe you enter a wrong choice");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("====================================================================");
	}
}
