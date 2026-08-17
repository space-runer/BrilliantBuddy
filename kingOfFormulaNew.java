package wonderful.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import wonderful.analyzer.Loted;
import wonderful.io.WonderfulFile;
import wonderful.tool.ArrayConverter;
import wonderful.tool.FileXpath;
import wonderful.tool.MapTool;
import wonderful.tool.Sorter;
import wonderful.tool.Viewer;

public class kingOfFormulaNew {

	private static int wantTerm = 2019072;

	private static int testTerm = 11;
	private static int minRightTerm = 11;
	private static LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();
	private static LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
	private static String historyFile = "wonderful.txt";

	public static void main(String[] args) {

		String path = FileXpath.getPath();
		WonderfulFile.readFile(path + historyFile);
		ArrayList<String> WonderfulLineDataArr = WonderfulFile.getLineDataArr();

		Loted.getLotedData(WonderfulLineDataArr);

		int start = 0;

		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();

		LinkedHashMap<String, ArrayList<Integer>> mapLote = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<String, ArrayList<ArrayList<Integer>>> mapKill = new LinkedHashMap<String, ArrayList<ArrayList<Integer>>>();

		ArrayList<ArrayList<Integer>> arrCon = Loted.getArrsRed();
		Collections.reverse(arrCon);
		Collections.reverse(Loted.getArrsTermAll());

		int cnt = 0;

		start = forecastWant(wantTerm - 1) - testTerm + 1;

		// oneF(start,arrCon);
		// twoFInSameRow(start,arrCon);
		for (int i = start; i > start - 1; i--) {
			twoFInNotSameRow(i, arrCon);
		}

		// for (int i = start; i < start + 10; i++) {
		//
		// ArrayList<Integer> arrNow = Loted.getArrsRed().get(i);
		// ArrayList<Integer> arrLast = Loted.getArrsRed().get(i + 1);
		//
		// ArrayList<Integer> tempArr = new ArrayList<Integer>(4);
		// Integer num = 0;
		// // num = arrLast.get(1) + 1;
		// // num = arrLast.get(0) + arrLast.get(2) - 16;
		// // num = arrLast.get(1) + 1;
		// num = arrLast.get(1) + 1;
		// tempArr.add(num);
		//
		// // num = 34 - arrLast.get(5);
		// // num = 33 - arrLast.get(1);
		// // num = arrLast.get(1) + arrLast.get(2)-18;
		// num = arrLast.get(1) + arrLast.get(3) + 4;
		// if (tempArr.indexOf(num) == -1) {
		// tempArr.add(num);
		// }
		//
		// // num = arrLast.get(1) + arrLast.get(2) + 3;
		// // num = 34 - arrLast.get(4);
		// // num = arrLast.get(0) + arrLast.get(4) -10;
		// num = arrLast.get(1) + arrLast.get(5) - 22;
		// if (tempArr.indexOf(num) == -1) {
		// tempArr.add(num);
		// }
		//
		// // num = arrLast.get(4) - 8;
		// // num = arrLast.get(2) + arrLast.get(4) - 24;
		// // num = arrLast.get(1) + arrLast.get(4) - 8;
		// num = arrLast.get(1) + 6;
		// if (tempArr.indexOf(num) == -1) {
		// tempArr.add(num);
		// }
		// cnt = cnt + DulplicateArrTool.dulplicateTimes(arrNow, tempArr);
		// System.out.println(DulplicateArrTool.dulplicateArrFull(arrNow,
		// tempArr));
		//
		// }
		//
		// System.out.println(cnt);

	}

	private static void oneF(int start, ArrayList<ArrayList<Integer>> arrCon) {
		for (int j = 0; j < 6; j++) {

			for (int row1 = start - 1; row1 > -1; row1--) {

				for (int k = 0; k < 34; k++) {
					ArrayList<Integer> rightArr = new ArrayList<Integer>();

					String key = "";
					if (k > 0) {
						key = (start - row1) + " r" + (j + 1) + " +" + k;
					} else {
						key = (start - row1) + " r" + (j + 1) + " " + k;
					}

					for (int z = start; z < start + testTerm; z++) {
						ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
						ArrayList<Integer> arrNow = arrCon.get(z);

						int val = arrLast.get(j) + k;
						if (val > 33) {
							val = val - 33;
							if (val > 33) {
								val = val - 33;
							}
						} else if (val < 0) {
							val = 33 + val;
							if (val < 0) {
								val = 33 + val;
							}
						}

						if (z == start + testTerm - 1) {
							rightArr.add(0);
						} else {
							if (arrNow.indexOf(val) != -1) {
								rightArr.add(val);
							}
						}
					}

					ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
					int val = arrLast.get(j) + k;
					if (val > 33) {
						val = val - 33;
						if (val > 33) {
							val = val - 33;
						}
					} else if (val < 0) {
						val = 33 + val;
						if (val < 0) {
							val = 33 + val;
						}
					}

					rightArr.add(val);

					if (rightArr.size() > minRightTerm) {
						mapHis.put(key, rightArr);
					}

				}
			}

		}

		Sorter.sortStringArrByValSizeDesc(mapHis);
		Viewer.viewMapStrArr(mapHis);
		mapHis.clear();
	}

	private static void twoFInSameRow(int start, ArrayList<ArrayList<Integer>> arrCon) {

		for (int j = 0; j < 5; j++) {
			for (int j1 = j + 1; j1 < 6; j1++) {
				for (int row1 = start - 1; row1 > -1; row1--) {

					for (int k = 0; k < 34; k++) {
						ArrayList<Integer> rightArr = new ArrayList<Integer>();

						String key = "";

						for (int z = start; z < start + testTerm; z++) {
							ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
							ArrayList<Integer> arrNow = arrCon.get(z);

							int val = arrLast.get(j) + arrLast.get(j1) + k;
							if (val > 33) {
								val = val - 33;
								if (val > 33) {
									val = val - 33;
								}
							} else if (val < 0) {
								val = 33 + val;
								if (val < 0) {
									val = 33 + val;
								}
							}

							if (z == start + testTerm - 1) {
								rightArr.add(0);
							} else {
								if (arrNow.indexOf(val) != -1) {
									rightArr.add(val);
								}
							}
						}

						ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
						int val = arrLast.get(j) + arrLast.get(j1) + k;
						if (val > 33) {
							val = val - 33;
							if (val > 33) {
								val = val - 33;
							}
						} else if (val < 0) {
							val = 33 + val;
							if (val < 0) {
								val = 33 + val;
							}
						}

						rightArr.add(val);

						if (rightArr.size() >= minRightTerm) {
							mapHis.put(key, rightArr);
							if (k > 0) {
								key = (start - row1) + "r" + (j + 1) + " + r" + (j1 + 1) + " +" + k;
							} else {
								key = (start - row1) + "r" + (j + 1) + " + r" + (j1 + 1) + " " + k;
							}
							System.out.print(key + " : " + rightArr.size() + " | " + rightArr.toString() + " - ");
							Viewer.viewIIMapOneLine(map);
							MapTool.addMapTimes(map, val);
						}

					}
				}
			}

		}

		Sorter.sortStringArrByValSizeDesc(mapHis);
		Viewer.viewMapStrArr(mapHis);
		mapHis.clear();
		Sorter.sortMapByValue(map);
		Viewer.viewIIMap(map);
		map.clear();
	}

	public static void twoFInNotSameRow(int start, ArrayList<ArrayList<Integer>> arrCon) {

		for (int j = 0; j < 6; j++) {
			for (int j1 = 0; j1 < 6; j1++) {
				for (int row1 = start - 1; row1 > 0; row1--) {
					for (int row2 = row1 - 1; row2 > -1; row2--) {

						for (int k = 0; k < 34; k++) {
							ArrayList<Integer> rightArr = new ArrayList<Integer>();

							for (int z = start; z < start + testTerm; z++) {
								ArrayList<Integer> arrLast = arrCon.get(z + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(z + row2 - start);

								ArrayList<Integer> arrNow = arrCon.get(z);

								int val = arrLast.get(j) + arrLast2.get(j1) + k;
								if (val > 33) {
									val = val - 33;
									if (val > 33) {
										val = val - 33;
									}
								} else if (val < 0) {
									val = 33 + val;
									if (val < 0) {
										val = 33 + val;
									}
								}

								if (arrNow.indexOf(val) != -1) {
									rightArr.add(val);
								} else {
									break;
								}
								// if(z == start + testTerm-1){
								// rightArr.add(0);
								// }else{
								// if (arrNow.indexOf(val) != -1) {
								// rightArr.add(val);
								// }
								// }
							}

							if (rightArr.size() == minRightTerm) {

								ArrayList<Integer> arrLast = arrCon.get(start + testTerm + row1 - start);
								ArrayList<Integer> arrLast2 = arrCon.get(start + testTerm + row2 - start);
								int val = arrLast.get(j) + arrLast2.get(j1) + k;
								if (val > 33) {
									val = val - 33;
									if (val > 33) {
										val = val - 33;
									}
								} else if (val < 0) {
									val = 33 + val;
									if (val < 0) {
										val = 33 + val;
									}
								}

								rightArr.add(val);

								String key = "";
								if (k > 0) {
									key = (start - row1) + " r" + (j + 1) + " " + (start - row2) + " + r" + (j1 + 1)
											+ " +" + k;
								} else {
									key = (start - row1) + " r" + (j + 1) + " " + (start - row2) + " + r" + (j1 + 1)
											+ " " + k;
								}
								mapHis.put(key, rightArr);
								System.out.print(key + " : " + rightArr.size() + " | " + rightArr.toString() + " - ");
								Viewer.viewIIMapOneLine(map);
								MapTool.addMapTimes(map, val);

							}

						}
					}
				}
			}

		}

		Sorter.sortStringArrByValSizeDesc(mapHis);
		Viewer.viewMapStrArr(mapHis);
		mapHis.clear();
		Sorter.sortMapByValue(map);
		System.out.println();
		Viewer.viewIIMapOneLine(map);
		map.clear();
	}

	public static LinkedHashMap<ArrayList<String>, ArrayList<Integer>> composite3(
			LinkedHashMap<String, ArrayList<Integer>> mapHis) {

		LinkedHashMap<ArrayList<String>, ArrayList<Integer>> mapRet = new LinkedHashMap<ArrayList<String>, ArrayList<Integer>>();

		ArrayList<String> arr = ArrayConverter
				.ConvertStrArrToStrArr(mapHis.keySet().toArray(new String[mapHis.size()]));

		for (int x = 0; x < arr.size() - 2; x++) {

			for (int y = x + 1; y < arr.size() - 1; y++) {

				for (int z = y + 1; z < arr.size(); z++) {
					ArrayList<Integer> lote = new ArrayList<Integer>();
					lote.addAll(mapHis.get(arr.get(x)));

					ArrayList<Integer> arrTemp1 = mapHis.get(arr.get(y));
					for (int num : arrTemp1) {
						if (lote.indexOf(num) == -1) {
							lote.add(num);
						}
					}

					ArrayList<Integer> arrTemp2 = mapHis.get(arr.get(z));
					for (int num : arrTemp2) {
						if (lote.indexOf(num) == -1) {
							lote.add(num);
						}
					}

					ArrayList<String> arrCom = new ArrayList<String>(3);
					arrCom.add(arr.get(x));
					arrCom.add(arr.get(y));
					arrCom.add(arr.get(z));
					mapRet.put(arrCom, lote);
				}
			}
		}

		return mapRet;
	}

	public static LinkedHashMap<ArrayList<String>, ArrayList<Integer>> composite4(
			LinkedHashMap<String, ArrayList<Integer>> mapHis) {

		LinkedHashMap<ArrayList<String>, ArrayList<Integer>> mapRet = new LinkedHashMap<ArrayList<String>, ArrayList<Integer>>();

		ArrayList<String> arr = ArrayConverter
				.ConvertStrArrToStrArr(mapHis.keySet().toArray(new String[mapHis.size()]));

		int cnt = 0;

		for (int x = 0; x < arr.size() - 3; x++) {

			for (int y = x + 1; y < arr.size() - 2; y++) {

				for (int z = y + 1; z < arr.size() - 1; z++) {

					for (int w = z + 1; w < arr.size(); w++) {

						ArrayList<Integer> lote = new ArrayList<Integer>();
						lote.addAll(mapHis.get(arr.get(x)));

						ArrayList<Integer> arrTemp1 = mapHis.get(arr.get(y));
						for (int num : arrTemp1) {
							if (lote.indexOf(num) == -1) {
								lote.add(num);
							}
						}

						ArrayList<Integer> arrTemp2 = mapHis.get(arr.get(z));
						for (int num : arrTemp2) {
							if (lote.indexOf(num) == -1) {
								lote.add(num);
							}
						}

						ArrayList<Integer> arrTemp3 = mapHis.get(arr.get(w));
						for (int num : arrTemp3) {
							if (lote.indexOf(num) == -1) {
								lote.add(num);
							}
						}
						// 26
						if (lote.size() <= 15) {
							ArrayList<String> arrCom = new ArrayList<String>(3);
							arrCom.add(arr.get(x));
							arrCom.add(arr.get(y));
							arrCom.add(arr.get(z));
							arrCom.add(arr.get(w));
							mapRet.put(arrCom, lote);
						}
						cnt++;
						if (cnt % 10000 == 0) {
							System.out.println(cnt);
						}
					}
				}
			}
		}

		return mapRet;
	}

	public static int forecastWant(int strTerm) {

		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(strTerm);
		if(Loted.getArrsTermAll().indexOf(arr)!=-1) {
			return Loted.getArrsTermAll().indexOf(arr);
		}else {
			return Loted.getArrsTermAll().size();
		}
		
	}
}
