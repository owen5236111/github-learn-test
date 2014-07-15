package com.jpe.smt.aMapUtil;
public class TestGraphUtils {
	public static void main(String[] args) {
		// 构造一个坐标点
		BmapPoint point = new BmapPoint(25.00, 18.00);
		BmapPoint[] points = { new BmapPoint(10.0, 10.0),
				new BmapPoint(20.0, 10.0), new BmapPoint(30.0, 15.0),
				new BmapPoint(20.0, 20.0), new BmapPoint(10.0, 20.0) };
         boolean flag = GraphUtils.isPointInPolygon(point, points);
		System.out.println(flag);
	}
}
