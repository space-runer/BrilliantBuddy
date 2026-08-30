package newAna;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import WonderfulFile.ExportData;
import wonderful.analyzer.Loted;
import wonderful.dataShaper.HisShaper;
import wonderful.main.kingOfFormulaNew;
import wonderful.tool.ArrayConverter;
import wonderful.tool.Constant;
import wonderful.tool.DulplicateArrTool;
import wonderful.tool.FileXpath;
import wonderful.tool.HistoryTool;
import wonderful.tool.MapTool;
import wonderful.tool.Sorter;
import wonderful.tool.StringLang;
import wonderful.tool.TimeComputer;
import wonderful.tool.Viewer;

public class FormulaNew1DMaxV001 {

	// if you want forcast 24062 then input 24062
	// *** use 1) kill 2(collectCnt~100,111111) and 2)remain 10 =
	// 2-5(collectCnt~200,11111) also can use 2) cut 1) then dan ma
	// collectCnt = 1000, 111111111,false 248
	// 071-330 072-190 073-90 074-120 075-130
	public static int wantTerm = 26059;
	// pattern seed:"11111111111111"len14 collectCnt from 100->1000 upTo wrong:5->2
	// 100,runTerm1000->wrong:102
	// default:70
	public static int collectCnt = 160;
	public static int runTerm = 1;
	public static int seeNhis = 1;
	public static boolean danMa = true;
	public static int startBias = 0;
	// 0: change pattern, 1:change collectCnt, 2:start bias
	public static int changeType = 2;
	public static int intTpyeChangeCnt = 1;
	public static boolean byHis = true;
	
	// 27/30
	// ~~false-100-"11111111111111111111111111111111111111111111111111111111111"
	// 1111111111111111111111111111111111111111111111111111111111111111111111111111111
	public static String pattern = "111111";
	public static int watchFirstN = 2;
	// 13"0" +2"0" -2positon
	// 0000000000000
	// 23"0"
	// 00000000000000000000000
	// 00000000000000000000000000000000000000000000000000000000000000000000000
	// 111111
	// 0101010101010

	public static int loteType = Constant.Const_DLT;
	public static boolean consoleMsgFlg = true;

	private static int testTerm = 0;
	private static int minRightTerm = 0;
	private static int forwardTerm = 2;
	private static int redStart = 0;
	private static int redEnd = 0;
	private static int redCntResult = Constant.Const_DLT_CNT_RESULT;
	private static int redCnt = Constant.Const_DLT_CNT;
	
	private static long startOffPos = 0l;
	private static long offCnt = 0;
	private static long walkCnt = 0;
	private static List<Entry<Integer,Integer>> listTop = new ArrayList<>();
	private static List<Entry<Integer,Integer>> listLow = new ArrayList<>();
	private static LinkedHashMap<List<Integer>,Integer> mapHighHis = new LinkedHashMap<>();
	
	private static List<LinkedHashMap<Integer, Integer>> listMap = new ArrayList<>();
	private static LinkedHashMap<Integer, Integer> everyMaxMap = new LinkedHashMap<>();
	private static int breakCnt = collectCnt * 25;

	public static void main(String[] args) {

		TimeComputer.startTimer("a");
		List<Integer> listTopOk = new ArrayList<>();
		List<Integer> listLowOk = new ArrayList<>();
		
		for(int z = 35; z < 42; z++) {
			
			pattern = String.format("%0" + z + "d", 0);
			listTop.clear();
			listLow.clear();
			List<Integer> listTopOkTemp = new ArrayList<>();
			List<Integer> listLowOkTemp = new ArrayList<>();
			
			for(int index = 0; index< 1; index++) {
				System.out.println("~~~~~~~~~ loop ~~~~~~~~" + index + "~~~~~~~~~~~~~");
				runFormula();
//				startOffPos = offCnt + walkCnt;
			}
			System.out.println("result: " + z);
			
			for(LinkedHashMap<Integer, Integer> mapTemp: listMap) {
				Viewer.viewIIMapOneLine(mapTemp);
			}
			
//			System.out.println(listTop);	
//			LinkedHashMap<Integer,Integer> mapTopTimes = new LinkedHashMap<>();
//			for(Entry<Integer,Integer> ent: listTop) {
//				if(ent.getValue() >= 10) {
//					MapTool.addMapTimes(mapTopTimes, ent.getKey());	
//				}
//			}
//			Sorter.sortMapByValueDesc(mapTopTimes);
//			Viewer.viewIIMapOneLine(mapTopTimes);
//			
//			for(Entry<Integer,Integer> ent:mapTopTimes.entrySet()) {
//				if(ent.getValue() >3 && listTopOkTemp.size() <5) {
//					listTopOkTemp.add(ent.getKey());
//				}
//			}
//			listTopOk.addAll(listTopOkTemp);
//			
//			System.out.println(listLow);
//			LinkedHashMap<Integer,Integer> mapLowTimes = new LinkedHashMap<>();
//			for(Entry<Integer,Integer> ent: listLow) {
//				MapTool.addMapTimes(mapLowTimes, ent.getKey());
//			}
//			Sorter.sortMapByValueDesc(mapLowTimes);
//			Viewer.viewIIMapOneLine(mapLowTimes);
//			
//			for(Entry<Integer,Integer> ent:mapLowTimes.entrySet()) {
//				if(ent.getValue() >1 && listLowOkTemp.size() <5) {
//					listLowOkTemp.add(ent.getKey());
//				}
//			}
//			if(listLowOkTemp.size()<3) {
//				listLowOk.addAll(listLowOkTemp);
//			}
			
		}
		Sorter.sortMapLIByValue(mapHighHis);
		List<Entry<Integer,Integer>> list = new ArrayList(mapHighHis.entrySet());
//		System.out.println(list.get(list.size()-1));
//		System.out.println(list.get(list.size()-2));
//		System.out.println(list.get(list.size()-3));
		System.out.println("-------------");
//		System.out.println(list.get(0));
//		System.out.println(list.get(1));
//		System.out.println(list.get(2));
		
		System.out.println(listTopOk);
		System.out.println(listLowOk);
		TimeComputer.stopTimer("a");
		
		Sorter.sortMapByValueDesc(everyMaxMap);
		Viewer.viewIIMapOneLine(everyMaxMap);
		
		System.out.println(TimeComputer.ComputeTime("a"));

//		runFormula(wantTerm, loteType);
	}

	public static List<Integer> runFormula() {

		if (loteType == Constant.Const_SSQ) {
			testTerm = 12;
			minRightTerm = 12;
			if (danMa == true) {
				// best 9,10
				minRightTerm = 9;
			}
			redCnt = Constant.Const_SSQ_CNT;
			redCntResult = Constant.Const_SSQ_CNT_RESULT;
			redStart = Constant.Const_SSQ_START;
			redEnd = Constant.Const_SSQ_END;

			String path = FileXpath.getPath();
			ExportData.loadFileSSQ(path + "wonderful.txt");
		} else if (loteType == Constant.Const_3D) {
			testTerm = 13;
			minRightTerm = 13;
			if (danMa == false) {
				testTerm = 45;
				minRightTerm = 45;
			}
			redCnt = Constant.Const_3D_CNT;
			redCntResult = Constant.Const_3D_CNT_RESULT;
			redStart = Constant.Const_SSQ_START;
			redEnd = Constant.Const_3D_END;

			ExportData.loadFile3d();
		} else if (loteType == Constant.Const_DLT) {
			testTerm = 12;
			minRightTerm = 12;
			redCnt = Constant.Const_DLT_CNT;
			redCntResult = Constant.Const_DLT_CNT_RESULT;
			redStart = Constant.Const_SSQ_START;
			redEnd = Constant.Const_DLT_END;

			ExportData.loadFileSport();
		}

		int start = 0;

		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();

		LinkedHashMap<String, ArrayList<Integer>> mapLote = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<String, ArrayList<ArrayList<Integer>>> mapKill = new LinkedHashMap<String, ArrayList<ArrayList<Integer>>>();

		ArrayList<ArrayList<Integer>> arrCon = Loted.getArrsRed();

//		ArrayList<LinkedHashMap<Integer, Integer>> arrMap = HistoryTool.getHisMap(arrCon, redStart, redEnd, 50, null,
//				null);

		Collections.reverse(arrCon);
		Collections.reverse(Loted.getArrsTermAll());

		Collections.reverse(arrCon);
		List<List<Integer>> listHis = HisShaper.doShape(arrCon, 0);
		Collections.reverse(listHis);
		Collections.reverse(arrCon);
		
		int hisTermSpan = 5;
		int term = 20;
		int index = kingOfFormulaNew.forecastWant(wantTerm);
		
		LinkedHashMap<Integer, Integer> hisTermSpanMap = new LinkedHashMap<>();
		for (int i = 1; i < 36; i++) {
			hisTermSpanMap.put(i, 0);
		}
		for (int i = 0; i < term; i++) {

			ArrayList<Integer> arrTemp = arrCon.get(index - (i + 1) * hisTermSpan);
			for (Integer num : arrTemp) {
				MapTool.addMapTimes(hisTermSpanMap, num);
			}
		}

		int cnt = 0;

		int mapCnt = 50;
		if (pattern.indexOf("1") != -1) {
			mapCnt = collectCnt;
		}

		LinkedHashMap<Integer, Integer> mapTotal = new LinkedHashMap<>();

		StringBuilder sbAll = new StringBuilder();
		int noX = 0;
		StringBuilder sbResult = new StringBuilder();

		for (int y = 0; y < runTerm; y++) {
			int collectCntVar = collectCnt;
			String patternVar = pattern;
			int startBiasVar = startBias;
			LinkedHashMap<String, List<Integer>> strTimesMap = new LinkedHashMap<>();
			LinkedHashMap<Integer, Integer> finalTimeMap = new LinkedHashMap<>();
			System.out.println("########################             runTerm change " + y
					+ "             ###########################");

			for (int x = 0; x < intTpyeChangeCnt; x++) {
				int seeNhisOkCnt = 0;
				int longHaveCnt = 0;
				boolean lastHaveFlg = false;
				boolean stopFlg = false;
				testTerm = patternVar.length();
				StringBuilder sb = new StringBuilder();

				System.out.println("########################  for type change");

				for (int i = 0; i < seeNhis; i++) {

					start = kingOfFormulaNew.forecastWant(wantTerm) - i - y - 2 - testTerm + 2;
					// start = TermToIndex.forecastWant(wantTerm-1)- testTerm + 1;

					int pos = 0;

					// 30
					LinkedHashMap<Integer, Integer> strPlus = twoFInNotSameRowOkNgSpPos2(start, startBiasVar, arrCon,
							arrCon, danMa, collectCntVar, collectCntVar, pos, redCntResult, patternVar);
					if (i == 0) {
						MapTool.addMapToMapTimes(mapTotal, strPlus);
					}

					List<Integer> integerList = null;
					try {
						integerList = new ArrayList<>(strPlus.keySet());
					} catch (Exception e) {
						e.printStackTrace();
					}

					LinkedHashMap<Integer, Integer> posMap = new LinkedHashMap<>();
					for (int j = 0; j < integerList.size(); j++) {
						posMap.put(integerList.get(j), j);
					}

					Collections.reverse(integerList);

					index = kingOfFormulaNew.forecastWant(wantTerm) - i - y;

					List<Integer> posList = DulplicateArrTool.getPosFromArr1(integerList, arrCon.get(index));

					List<Integer> hisList = listHis.get(index - 1);

					String withHis = "";
					LinkedHashMap<Integer, Integer> hisMap = new LinkedHashMap<>();
					for (Integer num : integerList) {
						withHis += " " + num + "(" + hisList.get(num - 1) + ")";
//						if (hisMap.size() < 11) {
						hisMap.put(num, hisList.get(num - 1) + posMap.get(num) * 2); // hisList.get(num - 1) +
																						// posMap.get(num) * 2
//						}
					}
					Sorter.sortMapByValueDesc(hisMap);
					List<Integer> topHisList = new ArrayList<>(hisMap.keySet());

					boolean isHave = false;
					boolean isHaveDisp = false;
					int pos1 = 0;// topHisList.size() - 1;
					int pos2 = 1;// topHisList.size() - 2;
//					if (arrCon.get(index).indexOf(topHisList.get(pos1)) != -1|| arrCon.get(index).indexOf(topHisList.get(pos2))!=-1
//							) { 
//																					
//						if (i != 0) {
//							isHave = true;
//						}
//						isHaveDisp = true;
//					} else {
//						if (i != 0) {
//							seeNhisOkCnt++;
//						}
//						isHave = false;
//						isHaveDisp = false;
//					}
					if (i > 1 && isHaveDisp == true && lastHaveFlg == false) {
						stopFlg = true;
					}
					if (i > 0 && stopFlg == false && isHaveDisp == false) {
						longHaveCnt++;
					}

//			if ((posList.get(0) > watchFirstN-1 || posList.get(0) == -1) && (posList.get(1) > watchFirstN-1 || posList.get(1) == -1)
//					&& (posList.get(2) > watchFirstN-1 || posList.get(2) == -1) && (posList.get(3) > watchFirstN-1 || posList.get(3) == -1)) {
//				seeNhisOkCnt++;
//				isHave = true;
//			}

					sb.append(Loted.getArrsTermAll().get(index).get(0).toString() + " " + arrCon.get(index).toString());
//					+ " " + (isHaveDisp ? " " : "X") + " "
//							+ arrCon.get(index).toString() + "   " + posList.toString() + "  #" + topHisList.get(pos1)
//							+ "/" + hisList.get(topHisList.get(pos1) - 1) + " " 
//							+ topHisList.get(pos2) + "/" + hisList.get(topHisList.get(pos2) - 1) 
//							+ " s: (len" + integerList.size() + ")" + withHis);
//					sb.append(System.lineSeparator());

					if (isHave == true) {
//						break;
					}
					lastHaveFlg = isHaveDisp;
				}
				System.out.println(sb.toString());
				System.out.println(seeNhisOkCnt);
				// seeNhisOkCnt
				List<Integer> longLenAndLen = new ArrayList<>();
				longLenAndLen.add(seeNhisOkCnt);
				longLenAndLen.add(longHaveCnt);
				strTimesMap.put(sb.toString(), longLenAndLen);
				if (changeType == 0) {
					patternVar = patternVar + "1";
				} else if (changeType == 1) {
					collectCntVar = collectCntVar + 20;
				} else {
					startBiasVar = startBiasVar + 20;
				}
			}
			Sorter.sortStringArrByValDesc(strTimesMap);
			ArrayList<String> selectList = new ArrayList<>(strTimesMap.keySet());
			String bestStr = selectList.get(selectList.size() - 1).toString();
			sbAll.append(bestStr);

			for (Map.Entry<String, List<Integer>> entry : strTimesMap.entrySet()) {
				String key = entry.getKey();
				List<Integer> val = entry.getValue();

//				if (val.get(1) <= 8 && val.get(1) >= 6) {
//					if (key.split(System.lineSeparator())[0].indexOf("X") != -1) {
//						noX++;
//					}
//					sbAll.append(key + System.lineSeparator());
//					break;
//				}
			}

			System.out.println("step: ");
			Iterator<Entry<String, List<Integer>>> iter = strTimesMap.entrySet().iterator();
			int selectCnt = 0;
			while (iter.hasNext()) {
				Entry<String, List<Integer>> entry = iter.next();
				String result = entry.getKey().split(System.lineSeparator())[0];

//				if (selectCnt < 5) {
//
//					int indexS = result.indexOf("#");
//					int indexE = result.indexOf(" s:");
//					String sListStr = result.substring(indexS + 1, indexE);
//					List<Integer> ret = Stream.of(sListStr.split(" ")).map(e -> Integer.parseInt(e.split("/")[0]))
//							.collect(Collectors.toList());
//					MapTool.addMapTimes(finalTimeMap, ret.get(0));
//					MapTool.addMapTimes(finalTimeMap, ret.get(1));
//				}

				System.out.println(entry.getKey());
				selectCnt++;
			}

//			Sorter.sortMapByValue(finalTimeMap);

//			List<Integer> anaList = new ArrayList<>(finalTimeMap.keySet());
//			LinkedHashMap<Integer, Integer> mapMod3 = new LinkedHashMap<>();
//			LinkedHashMap<Integer, Integer> mapBigSmall = new LinkedHashMap<>();
//			LinkedHashMap<Integer, Integer> mapOdd = new LinkedHashMap<>();
//			for (Integer num : anaList) {
//				MapTool.addMapTimes(mapOdd, num % 2);
//				MapTool.addMapTimes(mapMod3, num % 3);
//				MapTool.addMapTimes(mapBigSmall, num <= 18 ? 0 : 1);
//			}
//			Sorter.sortMapByValueDesc(mapOdd);
//			Sorter.sortMapByValueDesc(mapBigSmall);
//			Sorter.sortMapByValueDesc(mapMod3);
//			Viewer.viewIIMapOneLine(mapOdd);
//			Viewer.viewIIMapOneLine(mapMod3);
//			Viewer.viewIIMapOneLine(mapBigSmall);
//
//			List<Integer> listOdd = new ArrayList<>(mapOdd.keySet());
//			List<Integer> listBigSmall = new ArrayList<>(mapBigSmall.keySet());
//			List<Integer> listMod3 = new ArrayList<>(mapMod3.keySet());
//
//			boolean searchFlg = false;
//			for (Integer num : anaList) {
//				if (num % 2 == listOdd.get(0) && num % 3 == listMod3.get(0)
//						&& (num <= 18 ? 0 : 1) == listBigSmall.get(0)) {
//					searchFlg = true;
//					ArrayList<Integer> aimList = arrCon.get(kingOfFormulaNew.forecastWant(wantTerm) - y);
//					if (aimList.indexOf(num) == -1) {
//						sbResult.append(Loted.getArrsTermAll().get(kingOfFormulaNew.forecastWant(wantTerm) - y).get(0)
//								+ " " + aimList + "   " + num + System.lineSeparator());
//						noX++;
//					} else {
//						sbResult.append(
//								"w  " + Loted.getArrsTermAll().get(kingOfFormulaNew.forecastWant(wantTerm) - y).get(0)
//										+ " " + aimList + "   " + num + System.lineSeparator());
//					}
//					break;
//				}
//			}
//
//			if (searchFlg == false) {
//				for (Integer num : anaList) {
//					if (num % 2 == listOdd.get(0) && num % 3 == listMod3.get(0)
//							&& (num <= 18 ? 0 : 1) == listBigSmall.get(0)) {
//						searchFlg = true;
//						ArrayList<Integer> aimList = arrCon.get(kingOfFormulaNew.forecastWant(wantTerm) - y);
//						if (aimList.indexOf(num) == -1) {
//							sbResult.append(Loted.getArrsTermAll().get(kingOfFormulaNew.forecastWant(wantTerm) - y).get(0)
//									+ " " + aimList + "   " + num + System.lineSeparator());
//							noX++;
//						} else {
//							sbResult.append(
//									"w  " + Loted.getArrsTermAll().get(kingOfFormulaNew.forecastWant(wantTerm) - y).get(0)
//											+ " " + aimList + "   " + num + System.lineSeparator());
//						}
//						break;
//					}
//				}
//			}

			List<Integer> sList = new ArrayList<>();
			int mapCounter = 0;

			if (finalTimeMap.size() > 0) {
				cnt++;
			}

			ArrayList<Integer> aimList = arrCon.get(kingOfFormulaNew.forecastWant(wantTerm) - y);
			Sorter.sortMapByValue(mapTotal);

			int largeCnt = 0;
			int smallCnt = 0;
			int hisSum1 = 0;
			int hisSum2 = 0;
			
			List<Integer> hisList = listHis.get(index - 1);
			List<Integer> tempList = new ArrayList<>(mapTotal.keySet());
//			tempList.subList(index, index)
//			List<Integer> listB = tempList.subList(tempList.size()-9-10, tempList.size()-9);
//			List<Integer> listC = tempList.subList(tempList.size()-12, tempList.size()-12+10);
//			for (Integer num : listB) {
//				hisSum1 += hisList.get(num-1);
//			}
//			
//			for (Integer num : listC) {
//				hisSum2 += hisList.get(num-1);
//			}
//			System.out.println(listB + " his:" + hisSum1 +"   " + listC + " his:"  + hisSum2);
//			mapHighHis.put(listB, hisSum1);
//			mapHighHis.put(listC, hisSum2);
			
			LinkedHashMap<Integer, List<Integer>> mapHisTotal = new LinkedHashMap<>();
			for (Map.Entry<Integer, Integer> entry : mapTotal.entrySet()) {
				Integer key = entry.getKey();
				Integer val = entry.getValue();
				List<Integer> withHisList = new ArrayList<>();
				withHisList.add(val);
				withHisList.add(hisTermSpanMap.get(key));
				withHisList.add(hisList.get(key - 1));
				mapHisTotal.put(key, withHisList);
			}
			if (byHis) {
				Sorter.sortIntKeyMapByList(mapHisTotal);
			}
			
			System.out.println("run size:" + mapTotal.size());
			for (Map.Entry<Integer, List<Integer>> entry : mapHisTotal.entrySet()) {
				Integer key = entry.getKey();
				List<Integer> val = entry.getValue();
				if (aimList.indexOf(key) != -1) {
					System.out.print("#" + key + ": " + val + "  ");
				} else {
					System.out.print(key + ": " + val + "  ");
				}
				 
				if (mapCounter > mapTotal.size() - 13) {
					if (aimList.indexOf(key) != -1) {
						smallCnt++;
					}
				} else {
					if (aimList.indexOf(key) != -1) {
						largeCnt++;
					}
				}
				if (mapCounter == mapTotal.size() - 13) {
					System.out.print(" len:" + (mapCounter+1) + " c:" + largeCnt);
					System.out.println();
				}

				if (val.get(0) <= 3 && sList.size() < 4) {
					sList.add(key);
				}
				mapCounter++;
			}
			System.out.print(" c:" + smallCnt + System.lineSeparator());

			List<Integer> optionalList = new ArrayList<>(); // ( new ArrayList<>(mapTotal.keySet())).subList(0, 6);
			if (DulplicateArrTool.dulplicateTimes(optionalList, aimList) == 0) {
				sbResult.append(Loted.getArrsTermAll().get(kingOfFormulaNew.forecastWant(wantTerm) - y).get(0) + " "
						+ aimList + "   " + optionalList + System.lineSeparator());
				noX++;
			} else {
				sbResult.append("w  " + Loted.getArrsTermAll().get(kingOfFormulaNew.forecastWant(wantTerm) - y).get(0)
						+ " " + aimList + "   " + optionalList + System.lineSeparator());
			}

			System.out.println(System.lineSeparator());
		}

		String result = sbAll.toString();
//		System.out.println(result);
		System.out.println(sbResult.toString());
		System.out.println(noX + "/" + cnt);

		System.out.println("FormulaNew cost:" + TimeComputer.ComputeTime("a"));

//		int indexS = result.indexOf("#");
//		int indexE = result.indexOf(" s:");
//		String sListStr = result.substring(indexS + 1, indexE);
//
//		List<Integer> ret = Stream.of(sListStr.split(" ")).map(e -> Integer.parseInt(e.split("/")[0]))
//				.collect(Collectors.toList());

		return new ArrayList<>();

//		System.exit(0);
//
//		// Map<Integer, String> parallelMap = parallel(start, arrCon, danMa, 4,
//		// 8,0);
//		// for (int j = 0; j < redCntResult; j++) {
//		// System.out.println(parallelMap.get(j));
//		// }
//
//		System.out.println("plus " + strPlus);
////		String strMinus = twoFInNotSameRowOkNgSpPos(start, arrCon, arrCon,
////				danMa, 1, 8, pos, "01010101010101").get(pos);
////		System.out.println("minus " + strMinus);
//
//		// oneF(start,arrCon);
//		// twoFInSameRow(start,arrCon);
//		// for (int i = start; i > start - 1; i--) {
//		// twoFInNotSameRow(i, arrCon);
//		// }
//		// danMa = false; minRightTerm = 9; easy get dan!!!!!!
//		danMa = false;
//		minRightTerm = 12;
//		String kStr = twoFInNotSameRow(start, arrCon, arrCon, danMa, 4, 2);
//		danMa = true;
//		// two type fall pos
//		String d9Str = "";
//		String d10Str = "";
//		String d9oStr = "";
//		String d10oStr = "";
//		String kdStr1 = "";
//		String kdStr2 = "";
//		String kdStr3 = "";
//		String kdStr4 = "";
//
//		minRightTerm = 9;
//		d9Str = twoFInNotSameRow(start, arrCon, arrCon, danMa, 4, 8);
//		minRightTerm = 10;
//		d10Str = twoFInNotSameRow(start, arrCon, arrCon, danMa, 4, 8);
//		// minRightTerm = 11;
//		// String d11Str = twoFInNotSameRow(start, arrCon, danMa, 4, 8);
//
//		testTerm = 14;
//		start = kingOfFormulaNew.forecastWant(wantTerm) - 2 - testTerm + 2;
//
//		System.out.println("dan:");
//		System.out.println(d9Str);
//		System.out.println(d10Str);
//		// System.out.println(d11Str);
//
//		// Map<Integer, String> parallelMap = parallel(start, arrCon, danMa, 4,
//		// 8,0);
//		// for (int j = 0; j < 6; j++) {
//		// System.out.println(parallelMap.get(j));
//		// }
//
//		testTerm = 12;
//		minRightTerm = 10;
//		System.out.println("14-13");
//		// start = kingOfFormulaNew.forecastWant(wantTerm) - 2 - testTerm + 2;
//		// d10Str = threeFInNotSameRow(start, arrCon, danMa,
//		// 4, 8);
//
//		// Map<Integer, String> parallelMap = parallel(start, arrCon, danMa, 4,
//		// 8,
//		// 0);
//		// for (int j = 0; j < 6; j++) {
//		// System.out.println(parallelMap.get(j));
//		// }
//
//		// minRightTerm = 9;
//		// d9oStr = threeFInNotSameRow(start, arrCon, danMa, 4, 8);
//		// minRightTerm = 10;
//		// d10oStr = threeFInNotSameRow(start, arrCon, danMa, 4, 8);
//
//		// minRightTerm = 11;
//		// String d11Str = twoFInNotSameRow(start, arrCon, danMa, 4, 8);
//
//		// danMa = false;
//		// minRightTerm = 10;
//		// kdStr1 = twoFInNotSameRow(start, arrCon, danMa, 4, 8);
//		//
//		// danMa = false;
//		// minRightTerm = 9;
//		// kdStr2 = twoFInNotSameRow(start, arrCon, danMa, 4, 8);
//		//
//		danMa = false;
//		// 11,33交叉杀 22
//		testTerm = 33;
//		minRightTerm = 33;
//		start = kingOfFormulaNew.forecastWant(wantTerm) - 2 - testTerm + 2;
//		kdStr3 = twoFInNotSameRow(start, arrCon, arrCon, danMa, 4, 8);
//		//
//		// danMa = false;
//		// // 11,33交叉杀 22
//		// testTerm = 20;
//		// minRightTerm = 20;
//		// start = kingOfFormulaNew.forecastWant(wantTerm) - 2 - testTerm + 2;
//		// kdStr4 = twoFInNotSameRow(start, arrCon, danMa, 4, 8);
//
//		System.out.println("d81: " + kdStr1);
//		System.out.println("d82: " + kdStr2);
//		System.out.println("d83: " + kdStr3);
//
//		System.out.println("sha: ");
//		System.out.println(kStr);
//		System.out.println(kdStr3);
//		System.out.println(kdStr4);
//
//		ArrayList<String> arrAll = new ArrayList<String>();
//		ArrayConverter.addStrToList(kStr, arrAll);
//		ArrayConverter.addStrToList(kdStr3, arrAll);
//		ArrayConverter.addStrToList(kdStr4, arrAll);
//		System.out.println(arrAll.size());
//		System.out.println(arrAll);
//
//		System.out.println("dan:");
//		System.out.println(d9oStr);
//		System.out.println(d10oStr);
//		// System.out.println(d11Str);
//
//		LinkedHashMap<Integer, Integer> hisMap = arrMap.get(index);
//
//		String hisStr = "";
//		String seqStr = "";
//
//		for (int i = redStart; i <= redEnd; i++) {
//			seqStr += StringLang.toSame(i);
//			hisStr += StringLang.toSame(hisMap.get(i));
//		}
//		System.out.println(seqStr);
//		System.out.println(hisStr);

	}

	public static void runFormula(int wantTerm, int loteType) {
		String path = FileXpath.getPath();
		if (loteType == Constant.Const_SSQ) {
			testTerm = 12;
			minRightTerm = 12;
			if (danMa == true) {
				// best 9,10
				minRightTerm = 9;
			}
			redCnt = Constant.Const_SSQ_CNT;
			redCntResult = Constant.Const_SSQ_CNT_RESULT;
			redStart = Constant.Const_SSQ_START;
			redEnd = Constant.Const_SSQ_END;

			ExportData.loadFileSSQ(path + "wonderful.txt");
		} else if (loteType == Constant.Const_3D) {
			testTerm = 13;
			minRightTerm = 13;
			if (danMa == false) {
				testTerm = 45;
				minRightTerm = 45;
			}
			redCnt = Constant.Const_3D_CNT;
			redCntResult = Constant.Const_3D_CNT_RESULT;
			redStart = Constant.Const_3D_START;
			redEnd = Constant.Const_3D_END;

			ExportData.loadFile3d(path + "ZCoutput3d.txt");
		} else if (loteType == Constant.Const_DLT) {
			testTerm = 12;
			minRightTerm = 12;
			redCnt = Constant.Const_DLT_CNT;
			redCntResult = Constant.Const_DLT_CNT_RESULT;
			redStart = Constant.Const_DLT_START;
			redEnd = Constant.Const_DLT_END;

			ExportData.loadFileSport(path + "dlt.txt");
		} else if (loteType == Constant.Const_DLTB) {
			testTerm = 12;
			minRightTerm = 12;
			redCnt = Constant.Const_DLTB_CNT;
			redCntResult = Constant.Const_DLTB_CNT_RESULT;
			redStart = Constant.Const_DLT_START;
			redEnd = Constant.Const_DLTB_END;

			ExportData.loadFileSport(path + "dlt.txt");
		}

		int start = 0;

		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();

		LinkedHashMap<String, ArrayList<Integer>> mapLote = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<String, ArrayList<ArrayList<Integer>>> mapKill = new LinkedHashMap<String, ArrayList<ArrayList<Integer>>>();

		ArrayList<ArrayList<Integer>> arrCon = Loted.getArrsRed();
		ArrayList<ArrayList<Integer>> arrConBlue = Loted.getArrsBlue();
		if (loteType == Constant.Const_3D) {
			arrConBlue = arrCon;
		}

		ArrayList<LinkedHashMap<Integer, Integer>> arrMap = HistoryTool.getHisMap(arrCon, redStart, redEnd, 50, null,
				null);

		int index = kingOfFormulaNew.forecastWant(wantTerm);

		Collections.reverse(arrCon);
		if (arrCon != arrConBlue) {
			Collections.reverse(arrConBlue);
		}
		Collections.reverse(Loted.getArrsTermAll());

		int cnt = 0;

		int pos = 0;
		String strPlus = "";
		String strMinus = "";
		if (loteType == Constant.Const_DLTB) {
			strPlus = twoFInNotSameRowOkNgSpPos(start, 0, arrCon, arrConBlue, danMa, 1, 8, pos, redCntResult,
					"10101010101010").get(pos);

			strMinus = twoFInNotSameRowOkNgSpPos(start, 0, arrCon, arrConBlue, danMa, 1, 8, pos, redCntResult,
					"01010101010101").get(pos);
		}
		if (loteType == Constant.Const_3D) {
			testTerm = 18;
			start = kingOfFormulaNew.forecastWant(wantTerm) - 2 - testTerm + 2;
			strPlus = twoFInNotSameRowOkNgSpPos(start, 0, arrCon, arrConBlue, danMa, 1, 8, pos, redCntResult,
					"101010101010101010").get(pos);

			strMinus = twoFInNotSameRowOkNgSpPos(start, 0, arrCon, arrConBlue, danMa, 1, 8, pos, redCntResult,
					"010101010101010101").get(pos);
		} else {
			strPlus = twoFInNotSameRowOkNgSpPos(start, 0, arrCon, arrCon, danMa, 1, 8, pos, redCntResult, "1111")
					.get(pos);

//			strMinus = twoFInNotSameRowOkNgSpPos(start, arrCon, arrCon, danMa,
//					1, 8, pos, "01010101010101").get(pos);
		}

		ArrayList<Integer> strPlusArr = ArrayConverter.stringToList(strPlus, " ");
		ArrayList<Integer> strMinusArr = ArrayConverter.stringToList(strMinus, " ");
		strPlusArr.addAll(strMinusArr);
		String strTemp = getViewerLine(strPlusArr);
		System.out.println(strTemp);

		danMa = false;
		if (loteType == Constant.Const_DLT) {
			testTerm = 12;
			minRightTerm = 12;
		} else if (loteType == Constant.Const_DLTB) {
			testTerm = 24;
			minRightTerm = 24;
		} else if (loteType == Constant.Const_3D) {
			testTerm = 50;
			minRightTerm = 50;
		} else {
			testTerm = 12;
			minRightTerm = 12;
		}
		start = kingOfFormulaNew.forecastWant(wantTerm) - 2 - testTerm + 2;
		String kStr = "";
		if (loteType == Constant.Const_DLTB) {
			kStr = twoFInNotSameRow(start, arrCon, arrConBlue, danMa, 4, 2);
		} else if (loteType == Constant.Const_3D) {
			kStr = twoFInNotSameRow(start, arrCon, arrCon, danMa, 4, 2);
		} else {
			kStr = twoFInNotSameRow(start, arrCon, arrCon, danMa, 4, 2);
		}
		// System.out.println("test1");
		System.out.println();
		ArrayList<Integer> kStrArr = ArrayConverter.stringToList(kStr, " ");
		String temp = getViewerLine(kStrArr);
		System.out.println(temp);

		danMa = true;
		if (loteType == Constant.Const_DLT) {
			minRightTerm = 8;
		} else if (loteType == Constant.Const_DLTB) {
			minRightTerm = 7;
		} else if (loteType == Constant.Const_3D) {
			testTerm = 14;
			minRightTerm = 10;
		} else {
			minRightTerm = 9;
		}

		String d9Str = "";
		start = kingOfFormulaNew.forecastWant(wantTerm) - 2 - testTerm + 2;
		if (loteType == Constant.Const_DLTB) {
			d9Str = twoFInNotSameRow(start, arrCon, arrConBlue, danMa, 4, 8);
		} else if (loteType == Constant.Const_3D) {
			d9Str = twoFInNotSameRow(start, arrCon, arrCon, danMa, 4, 8);
		} else {
			d9Str = twoFInNotSameRow(start, arrCon, arrCon, danMa, 4, 8);
		}
		// System.out.println("test2");
		ArrayList<Integer> d9StrArr = ArrayConverter.stringToList(d9Str, " ");
		String temp1 = getViewerLine(d9StrArr);
		System.out.println(temp1);

		if (loteType == Constant.Const_DLT) {
			minRightTerm = 9;
		} else if (loteType == Constant.Const_DLTB) {
			minRightTerm = 8;
		} else if (loteType == Constant.Const_3D) {
			minRightTerm = 11;
		} else {
			minRightTerm = 10;
		}
		String d10Str = "";
		if (loteType == Constant.Const_DLTB) {
			d10Str = twoFInNotSameRow(start, arrCon, arrConBlue, danMa, 4, 8);
		} else if (loteType == Constant.Const_3D) {
			d10Str = twoFInNotSameRow(start, arrCon, arrCon, danMa, 4, 8);
		} else {
			d10Str = twoFInNotSameRow(start, arrCon, arrCon, danMa, 4, 8);
		}
		// System.out.println("test3");
		ArrayList<Integer> d10StrArr = ArrayConverter.stringToList(d10Str, " ");
		String temp2 = getViewerLine(d10StrArr);
		System.out.println(temp2);

		testTerm = 14;
		start = kingOfFormulaNew.forecastWant(wantTerm) - 2 - testTerm + 2;

	}

	private static String getViewerLine(ArrayList<Integer> kStrArr) {
		String temp = "           ";
		for (int i = redStart; i <= redEnd; i++) {
			if (kStrArr.indexOf(i) != -1) {
				temp += StringLang.toSameNoBackBlank(i) + "";
			} else {
				temp += "  ";
			}
		}
		return temp;
	}

	public static String twoFInNotSameRow(int start, ArrayList<ArrayList<Integer>> arrCon,
			ArrayList<ArrayList<Integer>> arrConBlue, boolean danMa, int danMaSize, int shaMaSize) {
		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

		int cnt = 0;
		for (int j = 0; j < redCntResult; j++) {
			for (int j1 = 0; j1 < redCntResult; j1++) {
				for (int row1 = start - 1; row1 > 0; row1--) {
					if (danMa == true && map.size() >= danMaSize) {
						break;
					}
					if (danMa == false && map.size() >= shaMaSize) {
						break;
					}
					for (int row2 = row1 - 1; row2 > -1; row2--) {

						if (danMa == false && map.size() >= shaMaSize) {
							break;
						}
						if (danMa == true && map.size() >= danMaSize) {
							break;
						}

						for (int k = 0; k < redCnt; k++) {
							ArrayList<Integer> rightArr = new ArrayList<Integer>();
							ArrayList<Integer> forwardArr = new ArrayList<Integer>();

							for (int z = start; z < start + testTerm; z++) {

								if (danMa == false && map.size() >= shaMaSize) {
									break;
								}

								ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

								ArrayList<Integer> arrNow = null;
								try {
									arrNow = arrConBlue.get(z);
								} catch (Exception e) {
									System.out.print("a");
								}

								int val = arrLast.get(j) + arrLast2.get(j1) + k;

								if (val > redEnd) {
									val = val - redCnt;
									while (val > redEnd) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								if (danMa) {
									if (arrNow.indexOf(val) != -1) {
										rightArr.add(val);
									} else {
										break;
									}
								} else {
									if (arrNow.indexOf(val) == -1) {
										rightArr.add(val);
									} else {
										break;
									}
								}

								// if(z == start + testTerm-1){
								// rightArr.add(0);
								// }else{
								// if (arrNow.indexOf(val) != -1) {
								// rightArr.add(val);
								// }
								// }
							}

							// forward
							for (int z = start + testTerm; z < start + testTerm + forwardTerm; z++) {

								try {

									ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
									ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

									int val = arrLast.get(j) + arrLast2.get(j1) + k;

									if (val > redEnd) {
										val = val - redCnt;
										while (val > redEnd) {
											val = val - redCnt;
										}
									} else if (val < 0) {
										val = redCnt + val;
										while (val < 0) {
											val = redCnt + val;
										}
									}

									forwardArr.add(val);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}

							if (rightArr.size() == minRightTerm) {

								ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(start + testTerm + row2 - start);
								int val = arrLast.get(j) + arrLast2.get(j1) + k;
								if (val > redEnd) {
									val = val - redCnt;
									while (val > redEnd) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								rightArr.add(val);

								cnt++;
								String key = "";
								if (k > 0) {
									key = cnt + " : " + (start - row1) + " r" + (j + 1) + " + " + (start - row2)
											+ " + r" + (j1 + 1) + " +" + k;
								} else {
									key = cnt + " : " + (start - row1) + " r" + (j + 1) + " + " + (start - row2)
											+ " + r" + (j1 + 1) + " " + k;
								}
								mapHis.put(key, rightArr);
								if (consoleMsgFlg) {
									System.out.print(key + " : " + rightArr.size() + " | " + rightArr.toString() + " - "
											+ forwardArr.toString());
									Viewer.viewIIMapOneLine(map);
								}

								MapTool.addMapTimes(map, val);

							}

						}
					}
				}
			}

		}
		// Sorter.sortStringArrByValSizeDesc(mapHis);
		if (consoleMsgFlg) {
			Viewer.viewMapStrArr(mapHis);
		}
		mapHis.clear();
		// Sorter.sortMapByValue(map);

		if (consoleMsgFlg) {
			System.out.println();
		}
		Viewer.consoleMsgFlg = consoleMsgFlg;
		String ret = Viewer.viewIIMapOneLine(map);
		map.clear();

		return ret;

	}

	public static String threeFInNotSameRow(int start,

			ArrayList<ArrayList<Integer>> arrCon, boolean danMa, int danMaSize, int shaMaSize) {
		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

		int cnt = 0;
		for (int j = 0; j < redCntResult; j++) {
			for (int j1 = 0; j1 < redCntResult; j1++) {
				for (int j2 = 0; j2 < redCntResult; j2++) {
					for (int row1 = start - 1; row1 > 0; row1--) {
						if (danMa == true && map.size() >= danMaSize) {
							break;
						}
						if (danMa == false && map.size() >= shaMaSize) {
							break;
						}
						for (int row2 = row1; row2 > -1; row2--) {
							if (danMa == true && map.size() >= danMaSize) {
								break;
							}
							if (danMa == false && map.size() >= shaMaSize) {
								break;
							}

							for (int row3 = row2; row3 > -1; row3--) {
								if (danMa == true && map.size() >= danMaSize) {
									break;
								}
								if (danMa == false && map.size() >= shaMaSize) {
									break;
								}

								for (int k = 0; k < redCnt; k++) {
									ArrayList<Integer> rightArr = new ArrayList<Integer>();
									ArrayList<Integer> forwardArr = new ArrayList<Integer>();

									for (int z = start; z < start + testTerm; z++) {
										ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
										ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);
										ArrayList<Integer> arrLast3 = arrCon.get(z + row3 - start);

										ArrayList<Integer> arrNow = null;
										try {
											arrNow = arrCon.get(z);
										} catch (Exception e) {
											System.out.print("a");
										}

										int val = arrLast.get(j) + arrLast2.get(j1) + arrLast3.get(j2) + k;

										if (val > redEnd) {
											val = val - redCnt;
											while (val > redEnd) {
												val = val - redCnt;
											}
										} else if (val < 0) {
											val = redCnt + val;
											while (val < 0) {
												val = redCnt + val;
											}
										}

										if (danMa) {
											if (arrNow.indexOf(val) != -1) {
												rightArr.add(val);
											}
										} else {
											if (arrNow.indexOf(val) == -1) {
												rightArr.add(val);
											}
										}

										// if(z == start + testTerm-1){
										// rightArr.add(0);
										// }else{
										// if (arrNow.indexOf(val) != -1) {
										// rightArr.add(val);
										// }
										// }
									}

									// forward
									for (int z = start + testTerm; z < start + testTerm + forwardTerm; z++) {

										try {

											ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
											ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);
											ArrayList<Integer> arrLast3 = arrCon.get(z + row3 - start);

											int val = arrLast.get(j) + arrLast2.get(j1) + arrLast3.get(j2) + k;

											if (val > redEnd) {
												val = val - redCnt;
												while (val > redEnd) {
													val = val - redCnt;
												}
											} else if (val < 0) {
												val = redCnt + val;
												while (val < 0) {
													val = redCnt + val;
												}
											}

											forwardArr.add(val);
										} catch (Exception e) {
											// TODO: handle exception
										}
									}

									if (rightArr.size() == minRightTerm) {

										ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
										ArrayList<Integer> arrLast2 = arrCon.get(start + testTerm + row2 - start);
										ArrayList<Integer> arrLast3 = arrCon.get(start + testTerm + row3 - start);
										int val = arrLast.get(j) + arrLast2.get(j1) + arrLast3.get(j2) + k;
										if (val > redCnt) {
											val = val - redCnt;
											while (val > redCnt) {
												val = val - redCnt;
											}
										} else if (val < 0) {
											val = redCnt + val;
											while (val < 0) {
												val = redCnt + val;
											}
										}

										rightArr.add(val);

										cnt++;
										String key = "";
										if (k > 0) {
											key = cnt + " : " + (start - row1) + " r" + (j + 1) + " + " + (start - row2)
													+ " + r" + (j1 + 1) + " + " + (start - row3) + " + r" + (j2 + 1)
													+ " + " + k;
										} else {
											key = cnt + " : " + (start - row1) + " r" + (j + 1) + " + " + (start - row2)
													+ " + r" + (j1 + 1) + " + " + (start - row3) + " + r" + (j2 + 1)
													+ " " + k;
										}
										mapHis.put(key, rightArr);
										System.out.print(key + " : " + rightArr.size() + " | " + rightArr.toString()
												+ " - " + forwardArr.toString());
										Viewer.viewIIMapOneLine(map);

										MapTool.addMapTimes(map, val);

									}

								}
							}
						}
					}
				}
			}
		}
		// Sorter.sortStringArrByValSizeDesc(mapHis);
		Viewer.viewMapStrArr(mapHis);
		mapHis.clear();
		// Sorter.sortMapByValue(map);
		System.out.println();
		String ret = Viewer.viewIIMapOneLine(map);
		map.clear();

		return ret;

	}

	public static HashMap<Integer, String> threeFInNotSameRowSpPos(int start,

			ArrayList<ArrayList<Integer>> arrCon, boolean danMa, int danMaSize, int shaMaSize, int pos) {
		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

		int cnt = 0;
		for (int j = pos; j < redCntResult; j++) {
			for (int j1 = 0; j1 < redCntResult; j1++) {
				for (int j2 = 0; j2 < redCntResult; j2++) {
					for (int row1 = start - 1; row1 > 0; row1--) {
						if (danMa == true && map.size() >= danMaSize) {
							break;
						}
						if (danMa == false && map.size() >= shaMaSize) {
							break;
						}
						for (int row2 = row1; row2 > -1; row2--) {
							if (danMa == true && map.size() >= danMaSize) {
								break;
							}
							if (danMa == false && map.size() >= shaMaSize) {
								break;
							}

							for (int row3 = row2; row3 > -1; row3--) {
								if (danMa == true && map.size() >= danMaSize) {
									break;
								}
								if (danMa == false && map.size() >= shaMaSize) {
									break;
								}

								for (int k = 0; k < redCnt; k++) {
									ArrayList<Integer> rightArr = new ArrayList<Integer>();
									ArrayList<Integer> forwardArr = new ArrayList<Integer>();

									for (int z = start; z < start + testTerm; z++) {
										ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
										ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);
										ArrayList<Integer> arrLast3 = arrCon.get(z + row3 - start);

										ArrayList<Integer> arrNow = null;
										try {
											arrNow = arrCon.get(z);
										} catch (Exception e) {
											System.out.print("a");
										}

										int val = arrLast.get(j) + arrLast2.get(j1) + arrLast3.get(j2) + k;

										if (val > redEnd) {
											val = val - redCnt;
											while (val > redEnd) {
												val = val - redCnt;
											}
										} else if (val < 0) {
											val = redCnt + val;
											while (val < 0) {
												val = redCnt + val;
											}
										}

										if (danMa) {
											if (arrNow.indexOf(val) != -1) {
												rightArr.add(val);
											}
										} else {
											if (arrNow.indexOf(val) == -1) {
												rightArr.add(val);
											}
										}

										// if(z == start + testTerm-1){
										// rightArr.add(0);
										// }else{
										// if (arrNow.indexOf(val) != -1) {
										// rightArr.add(val);
										// }
										// }
									}

									// forward
									for (int z = start + testTerm; z < start + testTerm + forwardTerm; z++) {

										try {

											ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
											ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);
											ArrayList<Integer> arrLast3 = arrCon.get(z + row3 - start);

											int val = arrLast.get(j) + arrLast2.get(j1) + arrLast3.get(j2) + k;

											if (val > redEnd) {
												val = val - redCnt;
												while (val > redEnd) {
													val = val - redCnt;
												}
											} else if (val < 0) {
												val = redCnt + val;
												while (val < 0) {
													val = redCnt + val;
												}
											}

											forwardArr.add(val);
										} catch (Exception e) {
											// TODO: handle exception
										}
									}

									if (rightArr.size() == minRightTerm) {

										ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
										ArrayList<Integer> arrLast2 = arrCon.get(start + testTerm + row2 - start);
										ArrayList<Integer> arrLast3 = arrCon.get(start + testTerm + row3 - start);
										int val = arrLast.get(j) + arrLast2.get(j1) + arrLast3.get(j2) + k;
										if (val > redCnt) {
											val = val - redCnt;
											while (val > redCnt) {
												val = val - redCnt;
											}
										} else if (val < 0) {
											val = redCnt + val;
											while (val < 0) {
												val = redCnt + val;
											}
										}

										rightArr.add(val);

										cnt++;
										String key = "";
										if (k > 0) {
											key = "th" + pos + ")" + cnt + " : " + (start - row1) + " r" + (j + 1)
													+ " + " + (start - row2) + " + r" + (j1 + 1) + " + "
													+ (start - row3) + " + r" + (j2 + 1) + " + " + k;
										} else {
											key = "th" + pos + ")" + cnt + " : " + (start - row1) + " r" + (j + 1)
													+ " + " + (start - row2) + " + r" + (j1 + 1) + " + "
													+ (start - row3) + " + r" + (j2 + 1) + " " + k;
										}
										mapHis.put(key, rightArr);
										System.out.print(key + " : " + rightArr.size() + " | " + rightArr.toString()
												+ " - " + forwardArr.toString());
										Viewer.viewIIMapOneLine(map);

										MapTool.addMapTimes(map, val);

									}

								}
							}
						}
					}
				}
			}
		}
		// Sorter.sortStringArrByValSizeDesc(mapHis);
		Viewer.viewMapStrArr(mapHis);
		mapHis.clear();
		// Sorter.sortMapByValue(map);
		System.out.println();
		String ret = Viewer.viewIIMapOneLine(map);
		map.clear();

		HashMap<Integer, String> mapRet = new HashMap<Integer, String>();
		mapRet.put(pos, ret);
		return mapRet;

	}

	public static String twoFInNotSameRowOkNg(int start,

			ArrayList<ArrayList<Integer>> arrCon, boolean danMa, int danMaSize, int shaMaSize) {
		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

		int cnt = 0;
		for (int j = 0; j < redCntResult; j++) {
			for (int j1 = 0; j1 < redCntResult; j1++) {
				for (int row1 = start - 1; row1 > 0; row1--) {
					if (danMa == true && map.size() >= danMaSize) {
						break;
					}
					if (danMa == false && map.size() >= shaMaSize) {
						break;
					}
					for (int row2 = row1 - 1; row2 > -1; row2--) {
						if (danMa == true && map.size() >= danMaSize) {
							break;
						}
						if (danMa == false && map.size() >= shaMaSize) {
							break;
						}

						for (int k = 0; k < redCnt; k++) {
							ArrayList<Integer> rightArr = new ArrayList<Integer>();
							ArrayList<Integer> forwardArr = new ArrayList<Integer>();
							ArrayList<Integer> okNgArr = new ArrayList<Integer>();

							for (int z = start; z < start + testTerm; z++) {
								ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

								ArrayList<Integer> arrNow = null;
								try {
									arrNow = arrCon.get(z);
								} catch (Exception e) {
									System.out.print("a");
								}

								int val = arrLast.get(j) + arrLast2.get(j1) + k;

								if (val > redEnd) {
									val = val - redCnt;
									while (val > redEnd) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								if (danMa) {
									if (arrNow.indexOf(val) != -1) {
										rightArr.add(val);
										okNgArr.add(1);
									} else {
										okNgArr.add(0);
									}
								} else {
									if (arrNow.indexOf(val) == -1) {
										rightArr.add(val);
										okNgArr.add(1);
									} else {
										okNgArr.add(0);
									}
								}

								// if(z == start + testTerm-1){
								// rightArr.add(0);
								// }else{
								// if (arrNow.indexOf(val) != -1) {
								// rightArr.add(val);
								// }
								// }
							}

							// forward
							for (int z = start + testTerm; z < start + testTerm + forwardTerm; z++) {

								try {

									ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
									ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

									int val = arrLast.get(j) + arrLast2.get(j1) + k;

									if (val > redEnd) {
										val = val - redCnt;
										while (val > redEnd) {
											val = val - redCnt;
										}
									} else if (val < 0) {
										val = redCnt + val;
										while (val < 0) {
											val = redCnt + val;
										}
									}

									forwardArr.add(val);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}

							if (rightArr.size() == minRightTerm) {

								ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(start + testTerm + row2 - start);
								int val = arrLast.get(j) + arrLast2.get(j1) + k;
								if (val > redCnt) {
									val = val - redCnt;
									while (val > redCnt) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								rightArr.add(val);

								cnt++;
								String key = "";
								if (k > 0) {
									key = cnt + " : " + (start - row1) + " r" + (j + 1) + " + " + (start - row2)
											+ " + r" + (j1 + 1) + " +" + k;
								} else {
									key = cnt + " : " + (start - row1) + " r" + (j + 1) + " + " + (start - row2)
											+ " + r" + (j1 + 1) + " " + k;
								}
								mapHis.put(key, rightArr);
								System.out.print(key + " : " + rightArr.size() + " | " + rightArr.toString() + " - "
										+ forwardArr.toString());
								Viewer.viewIIMapOneLine(map);

								MapTool.addMapTimes(map, val);

							}

						}
					}
				}
			}

		}
		// Sorter.sortStringArrByValSizeDesc(mapHis);
		Viewer.viewMapStrArr(mapHis);
		mapHis.clear();
		// Sorter.sortMapByValue(map);
		System.out.println();
		String ret = Viewer.viewIIMapOneLine(map);
		map.clear();

		return ret;

	}

	public static HashMap<Integer, String> twoFInNotSameRowOkNgSpPos(int start, int startBias,
			ArrayList<ArrayList<Integer>> arrCon, ArrayList<ArrayList<Integer>> arrConBlue, boolean danMa,
			int danMaSize, int shaMaSize, int pos, int posEnd, String pattern) {
		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

		boolean flgExitLoop = false;
		int cnt = 0;
		for (int j = pos; j < posEnd; j++) {
			for (int j1 = 0; j1 < redCntResult; j1++) {
				if (j == j1) {
					continue;
				}
				for (int row1 = start - 1 - startBias; row1 > 0; row1--) {
					// map
					if (danMa == true && mapHis.size() >= danMaSize) {
						break;
					}
					if (danMa == false && mapHis.size() >= shaMaSize) {
						break;
					}
					if (flgExitLoop) {
						break;
					}

					for (int row2 = row1 - 1; row2 > -1; row2--) {
						if (danMa == true && mapHis.size() >= danMaSize) {
							break;
						}
						if (danMa == false && mapHis.size() >= shaMaSize) {
							break;
						}
						if (flgExitLoop) {
							break;
						}

						for (int k = 0; k < redCnt; k++) {

							ArrayList<Integer> rightArr = new ArrayList<Integer>();
							ArrayList<Integer> forwardArr = new ArrayList<Integer>();
							ArrayList<Integer> okNgArr = new ArrayList<Integer>();
							String his = "";

							for (int z = start; z < start + testTerm; z++) {
								ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

								ArrayList<Integer> arrNow = null;
								arrNow = arrConBlue.get(z);

								int val = arrLast.get(j) + arrLast2.get(j1) + k;

								if (val > redEnd) {
									val = val - redCnt;
									while (val > redEnd) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								if (danMa) {
									if (arrNow.indexOf(val) != -1) {
										rightArr.add(val);
										okNgArr.add(1);
										his += "1";
									} else {
										rightArr.add(val);
										okNgArr.add(0);
										his += "0";
									}
								} else {
									if (arrNow.indexOf(val) == -1) {
										rightArr.add(val);
										okNgArr.add(1);
										his += "1";
									} else {
										okNgArr.add(0);
										his += "0";
									}
								}

								// if(z == start + testTerm-1){
								// rightArr.add(0);
								// }else{
								// if (arrNow.indexOf(val) != -1) {
								// rightArr.add(val);
								// }
								// }
							}

							// forward
							for (int z = start + testTerm; z < start + testTerm + forwardTerm; z++) {

								try {

									ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
									ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

									int val = arrLast.get(j) + arrLast2.get(j1) + k;

									if (val > redEnd) {
										val = val - redCnt;
										while (val > redEnd) {
											val = val - redCnt;
										}
									} else if (val < 0) {
										val = redCnt + val;
										while (val < 0) {
											val = redCnt + val;
										}
									}

									forwardArr.add(val);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}

							if (his.equals(pattern)) {

								ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(start + testTerm + row2 - start);
								int val = arrLast.get(j) + arrLast2.get(j1) + k;
								if (val > redEnd) {
									val = val - redCnt;
									while (val > redEnd) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								rightArr.add(val);

								cnt++;
								String key = "";
								if (k > 0) {
									key = "th" + pos + ")" + cnt + " : " + (start - row1) + " r" + (j + 1) + " + "
											+ (start - row2) + " + r" + (j1 + 1) + " +" + k;
								} else {
									key = "th" + pos + ")" + cnt + " : " + (start - row1) + " r" + (j + 1) + " + "
											+ (start - row2) + " + r" + (j1 + 1) + " " + k;
								}
								mapHis.put(key, rightArr);
								if (consoleMsgFlg) {
									System.out.print(key + " : " + rightArr.size() + " | " + rightArr.toString() + " - "
											+ forwardArr.toString());
									Viewer.viewIIMapOneLine(map);
								}

								MapTool.addMapTimes(map, val);

							}

						}
					}
				}
			}

		}
		// Sorter.sortStringArrByValSizeDesc(mapHis);
		if (consoleMsgFlg) {
			Viewer.viewMapStrArr(mapHis);
		}
		mapHis.clear();
		// Sorter.sortMapByValue(map);
		if (consoleMsgFlg) {
			System.out.println();
		}
		Viewer.consoleMsgFlg = consoleMsgFlg;
		map = Sorter.sortMapByValue(map);
		String ret = Viewer.viewIIMapOneLine(map);
		Viewer.viewIIMapKeyComma(map);
		map.clear();

		HashMap<Integer, String> mapRet = new HashMap<Integer, String>();
		mapRet.put(pos, ret);
		return mapRet;

	}

	public static LinkedHashMap<Integer, Integer> twoFInNotSameRowOkNgSpPos2(int start, int startBias,
			ArrayList<ArrayList<Integer>> arrCon, ArrayList<ArrayList<Integer>> arrConBlue, boolean danMa,
			int danMaSize, int shaMaSize, int pos, int posEnd, String pattern) {
		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

		boolean flgExitLoop = false;
		int cnt = 0;
		for (int j = pos; j < posEnd; j++) {
			for (int j1 = 0; j1 < redCntResult; j1++) {
				if (j == j1) {
					continue;
				}
				for (int row1 = start - 1 - startBias; row1 > 0; row1--) {
					// map
//					if (danMa == true && mapHis.size() >= danMaSize) {
//						break;
//					}
//					if (danMa == false && mapHis.size() >= shaMaSize) {
//						break;
//					}
					if (flgExitLoop) {
						break;
					}

					for (int row2 = row1 - 1; row2 > -1; row2--) {
//						if (danMa == true && mapHis.size() >= danMaSize) {
//							break;
//						}
//						if (danMa == false && mapHis.size() >= shaMaSize) {
//							break;
//						}
						if (flgExitLoop) {
							break;
						}

						for (int k = 0; k < redCnt; k++) {

							ArrayList<Integer> rightArr = new ArrayList<Integer>();
							ArrayList<Integer> forwardArr = new ArrayList<Integer>();
							ArrayList<Integer> okNgArr = new ArrayList<Integer>();
							String his = "";

							for (int z = start; z < start + testTerm; z++) {
								offCnt ++;
								if(offCnt < startOffPos) {
									continue;
								}else {
									walkCnt++;
								}
								
								ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

								ArrayList<Integer> arrNow = null;
								arrNow = arrConBlue.get(z);

								int val = arrLast.get(j) + arrLast2.get(j1) + k;

								if (val > redEnd) {
									val = val - redCnt;
									while (val > redEnd) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								if (danMa) {
									if (arrNow.indexOf(val) != -1) {
										rightArr.add(val);
										okNgArr.add(1);
										his += "1";
									} else {
										rightArr.add(val);
										okNgArr.add(0);
										his += "0";
									}
								} else {
									if (arrNow.indexOf(val) == -1) {
										rightArr.add(val);
										okNgArr.add(1);
										his += "1";
									} else {
										okNgArr.add(0);
										his += "0";
									}
								}

								// if(z == start + testTerm-1){
								// rightArr.add(0);
								// }else{
								// if (arrNow.indexOf(val) != -1) {
								// rightArr.add(val);
								// }
								// }
							}

							// forward
							for (int z = start + testTerm; z < start + testTerm + forwardTerm; z++) {

								try {

									ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
									ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

									int val = arrLast.get(j) + arrLast2.get(j1) + k;

									if (val > redEnd) {
										val = val - redCnt;
										while (val > redEnd) {
											val = val - redCnt;
										}
									} else if (val < 0) {
										val = redCnt + val;
										while (val < 0) {
											val = redCnt + val;
										}
									}

									forwardArr.add(val);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}

							if (his.equals(pattern)) {

								ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(start + testTerm + row2 - start);
								int val = arrLast.get(j) + arrLast2.get(j1) + k;
								if (val > redEnd) {
									val = val - redCnt;
									while (val > redEnd) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								rightArr.add(val);

								cnt++;
								String key = "";
								if (k > 0) {
									key = "th" + pos + ")" + cnt + " : done!";
//								+ (start - row1) + " r" + (j + 1) + " + "
//											+ (start - row2) + " + r" + (j1 + 1) + " +" + k;
								} else {
									key = "th" + pos + ")" + cnt + " : done!";
//									+ (start - row1) + " r" + (j + 1) + " + "
//											+ (start - row2) + " + r" + (j1 + 1) + " " + k;
								}
								mapHis.put(key, rightArr);
								if (consoleMsgFlg) {
									System.out.println(key + " : " + rightArr.size() + " | " + rightArr.toString() + " - "
											+ forwardArr.toString() );
//											key + " : " + rightArr.size() + " | " + rightArr.toString() + " - "
//											+ forwardArr.toString());
//									Viewer.viewIIMapOneLine(map);
								}

								MapTool.addMapTimes(map, val);
								if(mapHis.size() % danMaSize == 0 && mapHis.size() != 0) {
									map = Sorter.sortMapByValue(map);
									listMap.add(map);
									List<Entry<Integer, Integer>> entList = new ArrayList(map.entrySet());
									Entry<Integer, Integer> entLast = entList.get(entList.size()-1);
									if(entLast.getValue()>=13) {
										MapTool.addMapTimes(everyMaxMap, entLast.getKey());
									}
									map = new LinkedHashMap<Integer, Integer>();
								}
							}
							if(cnt == breakCnt) {
								break;
							}
						}
						if(cnt == breakCnt) {
							break;
						}
					}
					if(cnt == breakCnt) {
						break;
					}
				}
				if(cnt == breakCnt) {
					break;
				}
			}
			if(cnt == breakCnt) {
				break;
			}
		}
		System.out.println("startOffPos: " + startOffPos + "  walkCnt: " + walkCnt + " newOff: " + (startOffPos + walkCnt));
		
		// Sorter.sortStringArrByValSizeDesc(mapHis);
		if (consoleMsgFlg) {
//			Viewer.viewMapStrArr(mapHis);
		}
		mapHis.clear();
		// Sorter.sortMapByValue(map);
		if (consoleMsgFlg) {
			System.out.println();
		}
		Viewer.consoleMsgFlg = consoleMsgFlg;
		
//		map = Sorter.sortMapByValue(map);
//		Viewer.viewIIMapOneLine(map);
//		
//		List<Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
//		int listSize = list.size();
//		if(list.get(listSize-1).getValue() > 10 && list.get(0).getValue() == 1 && list.get(1).getValue() != 1) {
//			listLow.add(list.get(0));
//		}
//		
//		if(list.get(listSize-1).getValue() <= 10 && list.get(listSize - 1).getValue() != list.get(listSize -2).getValue() ) {
//			listTop.add(list.get(listSize - 1));
//		}

		return map;

	}

	public static String twoFInNotSameRowDown(int start,

			ArrayList<ArrayList<Integer>> arrCon, boolean danMa, int danMaSize, int shaMaSize) {
		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

		int cnt = 0;
		for (int j = redCntResult - 1; j >= 0; j--) {
			for (int j1 = redCntResult - 1; j1 >= 0; j1--) {
				for (int row1 = start - 1; row1 > 0; row1--) {
					if (danMa == true && map.size() >= danMaSize) {
						break;
					}
					if (danMa == false && map.size() >= shaMaSize) {
						break;
					}
					for (int row2 = row1 - 1; row2 > -1; row2--) {

						if (danMa == false && map.size() >= shaMaSize) {
							break;
						}

						for (int k = 0; k < redCnt; k++) {
							ArrayList<Integer> rightArr = new ArrayList<Integer>();
							ArrayList<Integer> forwardArr = new ArrayList<Integer>();

							for (int z = start; z < start + testTerm; z++) {
								ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

								ArrayList<Integer> arrNow = null;
								try {
									arrNow = arrCon.get(z);
								} catch (Exception e) {
									System.out.print("a");
								}

								int val = arrLast.get(j) + arrLast2.get(j1) + k;

								if (val > redEnd) {
									val = val - redCnt;
									while (val > redEnd) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								if (danMa) {
									if (arrNow.indexOf(val) != -1) {
										rightArr.add(val);
									} else {
										break;
									}
								} else {
									if (arrNow.indexOf(val) == -1) {
										rightArr.add(val);
									} else {
										break;
									}
								}

								// if(z == start + testTerm-1){
								// rightArr.add(0);
								// }else{
								// if (arrNow.indexOf(val) != -1) {
								// rightArr.add(val);
								// }
								// }
							}

							// forward
							for (int z = start + testTerm; z < start + testTerm + forwardTerm; z++) {

								try {

									ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
									ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

									int val = arrLast.get(j) + arrLast2.get(j1) + k;

									if (val > redEnd) {
										val = val - redCnt;
										while (val > redEnd) {
											val = val - redCnt;
										}
									} else if (val < 0) {
										val = redCnt + val;
										while (val < 0) {
											val = redCnt + val;
										}
									}

									forwardArr.add(val);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}

							if (rightArr.size() == minRightTerm) {

								ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(start + testTerm + row2 - start);
								int val = arrLast.get(j) + arrLast2.get(j1) + k;
								if (val > redCnt) {
									val = val - redCnt;
									while (val > redCnt) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								rightArr.add(val);

								cnt++;
								String key = "";
								if (k > 0) {
									key = cnt + " : " + (start - row1) + " r" + (j + 1) + " + " + (start - row2)
											+ " + r" + (j1 + 1) + " +" + k;
								} else {
									key = cnt + " : " + (start - row1) + " r" + (j + 1) + " + " + (start - row2)
											+ " + r" + (j1 + 1) + " " + k;
								}
								mapHis.put(key, rightArr);
								System.out.print(key + " : " + rightArr.size() + " | " + rightArr.toString() + " - "
										+ forwardArr.toString());
								Viewer.viewIIMapOneLine(map);

								MapTool.addMapTimes(map, val);

							}

						}
					}
				}
			}

		}
		// Sorter.sortStringArrByValSizeDesc(mapHis);
		Viewer.viewMapStrArr(mapHis);
		mapHis.clear();
		// Sorter.sortMapByValue(map);
		System.out.println();
		String ret = Viewer.viewIIMapOneLine(map);
		map.clear();

		return ret;

	}

	public static HashMap<Integer, String> twoFInNotSameRowSpPos(int start,

			ArrayList<ArrayList<Integer>> arrCon, boolean danMa, int danMaSize, int shaMaSize, int pos) {

		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

		int cnt = 0;
		for (int j = pos; j < redCntResult; j++) {
			for (int j1 = 0; j1 < redCntResult; j1++) {
				for (int row1 = start - 1; row1 > 0; row1--) {
					if (danMa == true && mapHis.size() >= danMaSize) {
						break;
					}
					if (danMa == false && mapHis.size() >= shaMaSize) {
						break;
					}

					for (int row2 = row1 - 1; row2 > -1; row2--) {
						if (danMa == true && mapHis.size() >= danMaSize) {
							break;
						}
						if (danMa == false && mapHis.size() >= shaMaSize) {
							break;
						}

						for (int k = 0; k < redCnt; k++) {
							ArrayList<Integer> rightArr = new ArrayList<Integer>();
							ArrayList<Integer> forwardArr = new ArrayList<Integer>();

							for (int z = start; z < start + testTerm; z++) {
								ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

								ArrayList<Integer> arrNow = null;
								try {
									arrNow = arrCon.get(z);
								} catch (Exception e) {
									System.out.print("a");
								}

								int val = arrLast.get(j) + arrLast2.get(j1) + k;

								if (val > redEnd) {
									val = val - redCnt;
									while (val > redEnd) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								if (danMa) {
									if (arrNow.indexOf(val) != -1) {
										rightArr.add(val);
									} else {
										break;
									}
								} else {
									if (arrNow.indexOf(val) == -1) {
										rightArr.add(val);
									} else {
										break;
									}
								}

								// if(z == start + testTerm-1){
								// rightArr.add(0);
								// }else{
								// if (arrNow.indexOf(val) != -1) {
								// rightArr.add(val);
								// }
								// }
							}

							// forward
							for (int z = start + testTerm; z < start + testTerm + forwardTerm; z++) {

								try {

									ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
									ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

									int val = arrLast.get(j) + arrLast2.get(j1) + k;

									if (val > redEnd) {
										val = val - redCnt;
										while (val > redEnd) {
											val = val - redCnt;
										}
									} else if (val < 0) {
										val = redCnt + val;
										while (val < 0) {
											val = redCnt + val;
										}
									}

									forwardArr.add(val);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}

							if (rightArr.size() == minRightTerm) {

								ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(start + testTerm + row2 - start);
								int val = arrLast.get(j) + arrLast2.get(j1) + k;
								if (val > redCnt) {
									val = val - redCnt;
									while (val > redCnt) {
										val = val - redCnt;
									}
								} else if (val < 0) {
									val = redCnt + val;
									while (val < 0) {
										val = redCnt + val;
									}
								}

								rightArr.add(val);

								cnt++;
								String key = "";
								if (k > 0) {
									key = "th" + pos + ")" + cnt + " : " + (start - row1) + " r" + (j + 1) + " + "
											+ (start - row2) + " + r" + (j1 + 1) + " +" + k;
								} else {
									key = "th" + pos + ")" + cnt + " : " + (start - row1) + " r" + (j + 1) + " + "
											+ (start - row2) + " + r" + (j1 + 1) + " " + k;
								}
								mapHis.put(key, rightArr);
								System.out.print(key + " : " + rightArr.size() + " | " + rightArr.toString() + " - "
										+ forwardArr.toString());
								Viewer.viewIIMapOneLine(map);

								MapTool.addMapTimes(map, val);

							}

						}
					}
				}
			}

		}
		// Sorter.sortStringArrByValSizeDesc(mapHis);
		Viewer.viewMapStrArr(mapHis);
		mapHis.clear();
		// Sorter.sortMapByValue(map);
		System.out.println();
		String ret = Viewer.viewIIMapOneLine(map);
		map.clear();

		HashMap<Integer, String> mapRet = new HashMap<Integer, String>();
		mapRet.put(pos, ret);
		return mapRet;

	}

	private static Map<Integer, String> parallel(int start,

			ArrayList<ArrayList<Integer>> arrCon, boolean danMa, int danMaSize, int shaMaSize, int type,
			String pattern) {

		Map<Integer, String> map = new HashMap<Integer, String>();

		ExecutorService executor = Executors.newFixedThreadPool(6);
		List<Future<Map<Integer, String>>> results = new ArrayList<Future<Map<Integer, String>>>();
		System.out.println("start parallel");

		for (int j = 0; j < redCntResult; j++) {
			runEachResult(start, arrCon, danMa, danMaSize, shaMaSize, executor, results, j, type, pattern);
		}

		for (Future<Map<Integer, String>> ft : results) {
			try {
				Iterator<Entry<Integer, String>> iterator = ft.get().entrySet().iterator();
				Entry<Integer, String> next = iterator.next();
				map.put(next.getKey(), next.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	private static void runEachResult(final int startWant, final ArrayList<ArrayList<Integer>> arrCon,
			final boolean danMa, int danMaSize, int shaMaSize, ExecutorService executor,
			List<Future<Map<Integer, String>>> results, final int j, final int type, String pattern) {

		Future<Map<Integer, String>> future = executor.submit(new Callable<Map<Integer, String>>() {

			public Map<Integer, String> call() {
				if (type == 0) {
					return twoFInNotSameRowSpPos(startWant, arrCon, danMa, 4, 8, j);
				} else if (type == 1) {
					return twoFInNotSameRowOkNgSpPos(startWant, 0, arrCon, arrCon, danMa, danMaSize, shaMaSize, j,
							j + 1, pattern);
				} else {
					return threeFInNotSameRowSpPos(startWant, arrCon, danMa, 4, 8, j);
				}

			}
		});
		results.add(future);
	}

}
