package wonderful.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import wonderful.analyzer.Loted;
import wonderful.io.WonderfulFile;
import wonderful.tool.ArrayConverter;
import wonderful.tool.DulplicateArrTool;
import wonderful.tool.MapTool;
import wonderful.tool.Sorter;
import wonderful.tool.Viewer;

public class kingOfFormula {

	public static void main(String[] args) {

		WonderfulFile.readFile("");
		ArrayList<String> WonderfulLineDataArr = WonderfulFile.getLineDataArr();

		Loted.getLotedData(WonderfulLineDataArr);

		int start = 1;

		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();

		LinkedHashMap<String, ArrayList<Integer>> mapHis = new LinkedHashMap<String, ArrayList<Integer>>();

		LinkedHashMap<String, ArrayList<Integer>> mapLote = new LinkedHashMap<String, ArrayList<Integer>>();
		LinkedHashMap<String, ArrayList<ArrayList<Integer>>> mapKill = new LinkedHashMap<String, ArrayList<ArrayList<Integer>>>();

		int cnt = 0;

		for (int i = start; i < start + 10; i++) {

			ArrayList<Integer> arrNow = Loted.getArrsRed().get(i);
			ArrayList<Integer> arrLast = Loted.getArrsRed().get(i + 1);

			ArrayList<Integer> tempArr = new ArrayList<Integer>(4);
			Integer num = 0;
			// num = arrLast.get(1) + 1;
			// num = arrLast.get(0) + arrLast.get(2) - 16;
			// num = arrLast.get(1) + 1;
			num = arrLast.get(1) + 1;
			tempArr.add(num);

			// num = 34 - arrLast.get(5);
			// num = 33 - arrLast.get(1);
			// num = arrLast.get(1) + arrLast.get(2)-18;
			num = arrLast.get(1) + arrLast.get(3) + 4;
			if (tempArr.indexOf(num) == -1) {
				tempArr.add(num);
			}

			// num = arrLast.get(1) + arrLast.get(2) + 3;
			// num = 34 - arrLast.get(4);
			// num = arrLast.get(0) + arrLast.get(4) -10;
			num = arrLast.get(1) + arrLast.get(5) - 22;
			if (tempArr.indexOf(num) == -1) {
				tempArr.add(num);
			}

			// num = arrLast.get(4) - 8;
			// num = arrLast.get(2) + arrLast.get(4) - 24;
			// num = arrLast.get(1) + arrLast.get(4) - 8;
			num = arrLast.get(1) + 6;
			if (tempArr.indexOf(num) == -1) {
				tempArr.add(num);
			}
			cnt = cnt + DulplicateArrTool.dulplicateTimes(arrNow, tempArr);
			System.out.println(DulplicateArrTool.dulplicateArrFull(arrNow, tempArr));

		}

		System.out.println(cnt);

		for (int i = start; i < start + 30; i++) {

			ArrayList<Integer> arrNow = Loted.getArrsRed().get(i);
			ArrayList<Integer> arrLast = Loted.getArrsRed().get(i + 1);

			for (int j = 0; j < 6; j++) {
				for (int k = -33; k < 34; k++) {
					String key = "";
					if (k > 0) {
						key = "r" + (j + 1) + " +" + k;
					} else {
						key = "r" + (j + 1) + " " + k;
					}
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
					if (mapKill.containsKey(key)) {
						ArrayList<Integer> arrTemp = new ArrayList<Integer>();
						mapKill.get(key).add(arrTemp);
						arrTemp.add(val);
					} else {
						ArrayList<ArrayList<Integer>> arrTempList = new ArrayList<ArrayList<Integer>>();

					}
					if (arrNow.indexOf(val) != -1) {
						if (map.containsKey(key)) {
							map.put(key, map.get(key) + 1);
						} else {
							map.put(key, 1);
						}
						if (mapHis.containsKey(key)) {
							ArrayList<Integer> arrTemp = mapHis.get(key);
							if (arrTemp.indexOf(i) == -1) {
								arrTemp.add(i);
							}
						} else {
							ArrayList<Integer> arrTemp = new ArrayList<Integer>();
							arrTemp.add(i);
							mapHis.put(key, arrTemp);
						}
						if (mapLote.containsKey(key)) {
							mapLote.get(key).add(val);
						} else {
							ArrayList<Integer> arrTemp = new ArrayList<Integer>();
							arrTemp.add(val);
							mapLote.put(key, arrTemp);
						}
					} else {
						if (mapLote.containsKey(key)) {
							mapLote.get(key).add(null);
						} else {
							ArrayList<Integer> arrTemp = new ArrayList<Integer>();
							arrTemp.add(null);
							mapLote.put(key, arrTemp);
						}
					}
				}
				for (int k = 33; k < 36; k++) {
					String key = "";
					if (k > 0) {
						key = k + "- r" + (j + 1) + " ";
					} else {
						key = k + "- r" + (j + 1) + " ";
					}
					int val = k - arrLast.get(j);
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
						if (map.containsKey(key)) {
							map.put(key, map.get(key) + 1);
						} else {
							map.put(key, 1);
						}
						if (mapHis.containsKey(key)) {
							ArrayList<Integer> arrTemp = mapHis.get(key);
							if (arrTemp.indexOf(i) == -1) {
								arrTemp.add(i);
							}
						} else {
							ArrayList<Integer> arrTemp = new ArrayList<Integer>();
							arrTemp.add(i);
							mapHis.put(key, arrTemp);
						}
						if (mapLote.containsKey(key)) {
							mapLote.get(key).add(val);
						} else {
							ArrayList<Integer> arrTemp = new ArrayList<Integer>();
							arrTemp.add(val);
							mapLote.put(key, arrTemp);
						}
					} else {
						if (mapLote.containsKey(key)) {
							mapLote.get(key).add(null);
						} else {
							ArrayList<Integer> arrTemp = new ArrayList<Integer>();
							arrTemp.add(null);
							mapLote.put(key, arrTemp);
						}
					}
				}
				// r1+r2
				for (int j1 = j + 1; j1 < 6; j1++) {
					for (int k = -33; k < 34; k++) {
						String key = "";
						if (k > 0) {
							key = "r" + (j + 1) + " + r" + (j1 + 1) + " +" + k;
						} else {
							key = "r" + (j + 1) + " + r" + (j1 + 1) + " " + k;
						}
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
						if (arrNow.indexOf(val) != -1) {
							if (map.containsKey(key)) {
								map.put(key, map.get(key) + 1);
							} else {
								map.put(key, 1);
							}
							if (mapHis.containsKey(key)) {
								ArrayList<Integer> arrTemp = mapHis.get(key);
								if (arrTemp.indexOf(i) == -1) {
									arrTemp.add(i);
								}
							} else {
								ArrayList<Integer> arrTemp = new ArrayList<Integer>();
								arrTemp.add(i);
								mapHis.put(key, arrTemp);
							}
							if (mapLote.containsKey(key)) {
								mapLote.get(key).add(val);
							} else {
								ArrayList<Integer> arrTemp = new ArrayList<Integer>();
								arrTemp.add(val);
								mapLote.put(key, arrTemp);
							}
						} else {
							if (mapLote.containsKey(key)) {
								mapLote.get(key).add(null);
							} else {
								ArrayList<Integer> arrTemp = new ArrayList<Integer>();
								arrTemp.add(null);
								mapLote.put(key, arrTemp);
							}
						}
					}
				}
				// r2-r1
				for (int j1 = j + 1; j1 < 6; j1++) {
					for (int k = -33; k < 34; k++) {
						String key = "";
						if (k > 0) {
							key = "r" + (j1 + 1) + " - r" + (j + 1) + " +" + k;
						} else {
							key = "r" + (j1 + 1) + " - r" + (j + 1) + " " + k;
						}
						int val = arrLast.get(j1) - arrLast.get(j) + k;
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
							if (map.containsKey(key)) {
								map.put(key, map.get(key) + 1);
							} else {
								map.put(key, 1);
							}
							if (mapHis.containsKey(key)) {
								ArrayList<Integer> arrTemp = mapHis.get(key);
								if (arrTemp.indexOf(i) == -1) {
									arrTemp.add(i);
								}
							} else {
								ArrayList<Integer> arrTemp = new ArrayList<Integer>();
								arrTemp.add(i);
								mapHis.put(key, arrTemp);
							}
							if (mapLote.containsKey(key)) {
								mapLote.get(key).add(val);
							} else {
								ArrayList<Integer> arrTemp = new ArrayList<Integer>();
								arrTemp.add(val);
								mapLote.put(key, arrTemp);
							}
						} else {
							if (mapLote.containsKey(key)) {
								mapLote.get(key).add(null);
							} else {
								ArrayList<Integer> arrTemp = new ArrayList<Integer>();
								arrTemp.add(null);
								mapLote.put(key, arrTemp);
							}
						}
					}
				}
			}
		}

		ArrayList<String> tempRemove = new ArrayList<String>();
		for (Entry<String, Integer> ent : map.entrySet()) {
			// 30 <= 8 60
			if (ent.getValue() >= 2) {
				mapHis.remove(ent.getKey());
				tempRemove.add(ent.getKey());
			}

		}

		for (String str : tempRemove) {
			map.remove(str);
		}

		Sorter.sortStringMapByValue(map);

		Viewer.viewSIMap(map, mapHis);

		System.out.println(map.size());

		LinkedHashMap<ArrayList<String>, ArrayList<Integer>> mapP = composite4(mapHis);

		Sorter.sortStrArrKeyMapByValDesc(mapP);

		LinkedHashMap<ArrayList<String>, ArrayList<Integer>> map1 = MapTool.getSubMapAA(mapP, 1, 10);

		LinkedHashMap<ArrayList<String>, ArrayList<Integer>> map2 = MapTool.getSubMapAA(mapP, map.size() - 10,
				mapP.size());

		Viewer.viewMapStrArrStrArr(map1);

		System.out.println("--------------------");

		// Viewer.viewMapStrArrStrArr(map2);

		// ----------------------------
		for (Entry<ArrayList<String>, ArrayList<Integer>> ent : mapP.entrySet()) {
			if (ent.getValue().size() == 30) {
				System.out.println(ent.getKey());
				for (int i = 0; i < 30; i++) {
					for (String str : ent.getKey()) {
						if (mapLote.get(str).get(i) != null) {
							System.out.print(mapLote.get(str).get(i) + ",");
						}
					}
					System.out.print(" | ");
				}
				System.out.println();
			}
		}

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
}
