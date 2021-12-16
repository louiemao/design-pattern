package com.louie.study.dp.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.hsr.geohash.GeoHash;
import com.google.common.collect.Lists;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

/**
 * @author 苍韧
 * @date 2021/9/13
 */
public class GeometryUtil {
    //private static GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
    //private static CRSFactory crsFactory = ReferencingFactoryFinder.getCRSFactory(null);
    ////WGS2000经纬度坐标系
    //private static String GCS_China_2000 = "GEOGCS[\"GCS_China_Geodetic_Coordinate_System_2000\","
    //    + "DATUM[\"D_China_2000\",SPHEROID[\"CGCS2000\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],"
    //    + "UNIT[\"Degree\",0.0174532925199433]]";
    ////墨卡托平面坐标系，用距离计算、面积计算
    //private static String Mercator_XY_WKT = "PROJCS[\"WGS_1984_Web_Mercator\","
    // +"GEOGCS[\"GCS_WGS_1984_Major_Auxiliary_Sphere\",DATUM[\"D_WGS_1984_Major_Auxiliary_Sphere\","
    //    +"SPHEROID[\"WGS_1984_Major_Auxiliary_Sphere\",6378137.0,0.0]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0"
    //    +".0174532925199433]],PROJECTION[\"Mercator\"],PARAMETER[\"False_Easting\",0.0],PARAMETER[\"False_Northing\",0"
    //    +".0],PARAMETER[\"Central_Meridian\",0.0],PARAMETER[\"Standard_Parallel_1\",0.0],UNIT[\"Meter\",1.0]]";
    //static final String strWKTMercator = "PROJCS[\"World_Mercator\","
    //    + "GEOGCS[\"GCS_WGS_1984\","
    //    + "DATUM[\"WGS_1984\","
    //    + "SPHEROID[\"WGS_1984\",6378137,298.257223563]],"
    //    + "PRIMEM[\"Greenwich\",0],"
    //    + "UNIT[\"Degree\",0.017453292519943295]],"
    //    + "PROJECTION[\"Mercator_1SP\"],"
    //    + "PARAMETER[\"False_Easting\",0],"
    //    + "PARAMETER[\"False_Northing\",0],"
    //    + "PARAMETER[\"Central_Meridian\",0],"
    //    + "PARAMETER[\"latitude_of_origin\",0],"
    //    + "UNIT[\"Meter\",1]]";
    //private static final String google="PROJCS[\"Google Projection\",GEOGCS[\"WGS 84\",DATUM[\"World Geodetic System "
    //    + "1984\",SPHEROID[\"WGS 84\", 6378137.0, 298.257223563, AUTHORITY[\"EPSG\",\"7030\"]],AUTHORITY[\"EPSG\","
    //    + "\"6326\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0"
    //    + ".017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],"
    //    + "AUTHORITY[\"EPSG\",\"4326\"]],PROJECTION[\"Popular Visualisation Pseudo Mercator\", AUTHORITY[\"EPSG\","
    //    + "\"1024\"]],PARAMETER[\"semi_minor\", 6378137.0],PARAMETER[\"latitude_of_origin\", 0.0],"
    //    + "PARAMETER[\"central_meridian\", 0.0],PARAMETER[\"scale_factor\", 1.0],PARAMETER[\"false_easting\", 0.0],"
    //    + "PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0]]";
    //
    //private static final String china2000 = "GEOCCS[\"China Geodetic Coordinate System 2000\","
    //    + "DATUM[\"China_2000\","
    //    + "SPHEROID[\"CGCS2000\",6378137,298.257222101,"
    //    + "AUTHORITY[\"EPSG\",\"1024\"]],"
    //    + "AUTHORITY[\"EPSG\",\"1043\"]],"
    //    + "PRIMEM[\"Greenwich\",0,"
    //    + "AUTHORITY[\"EPSG\",\"8901\"]],"
    //    + "UNIT[\"metre\",1,"
    //    + "AUTHORITY[\"EPSG\",\"9001\"]],"
    //    + "AXIS[\"Geocentric X\",OTHER],"
    //    + "AXIS[\"Geocentric Y\",OTHER],"
    //    + "AXIS[\"Geocentric Z\",NORTH],"
    //    + "AUTHORITY[\"EPSG\",\"4479\"]]";

    public static String albers_north_asia = "PROJCS[\"Asia_North_Albers_Equal_Area_Conic\",\n"
        + "    GEOGCS[\"GCS_WGS_1984\",\n"
        + "        DATUM[\"WGS_1984\",\n"
        + "            SPHEROID[\"WGS_1984\",6378137,298.257223563]],\n"
        + "        PRIMEM[\"Greenwich\",0],\n"
        + "        UNIT[\"Degree\",0.017453292519943295]],\n"
        + "    PROJECTION[\"Albers_Conic_Equal_Area\"],\n"
        + "    PARAMETER[\"False_Easting\",0],\n"
        + "    PARAMETER[\"False_Northing\",0],\n"
        + "    PARAMETER[\"longitude_of_center\",95],\n"
        + "    PARAMETER[\"Standard_Parallel_1\",15],\n"
        + "    PARAMETER[\"Standard_Parallel_2\",65],\n"
        + "    PARAMETER[\"latitude_of_center\",30],\n"
        + "    UNIT[\"Meter\",1],\n"
        + "    AUTHORITY[\"EPSG\",\"102025\"]]";

    ///**
    // * 根据点串创建多边形
    // *
    // * @param points
    // * @return
    // */
    //public static Polygon createPolygon(List<Coordinate> points) {
    //    Coordinate[] coords = (Coordinate[])points.toArray(new Coordinate[points.size()]);
    //    Polygon polygon = geometryFactory.createPolygon(coords);
    //    return polygon;
    //}
    //
    //
    ///**
    // * 获取多边形面积，输入坐标系为北京2000经纬度坐标系
    // *
    // * @param polygon
    // * @return
    // * @throws MismatchedDimensionException
    // * @throws FactoryException
    // * @throws TransformException
    // */
    //public static double getAreaLonLat(Polygon polygon)
    //    throws MismatchedDimensionException, FactoryException, TransformException {
    //    Coordinate[] points = polygon.getCoordinates();
    //    List<Coordinate> pointsList = new ArrayList<Coordinate>();
    //    for (int i = 0; i < points.length; i++) {
    //        Coordinate point = lonLatToXY(points[i]);
    //        pointsList.add(point);
    //    }
    //    Polygon lineXY = createPolygon(pointsList);
    //    return lineXY.getArea();
    //}
    //
    ///**
    // * 将经纬度的点坐标转换为平面坐标
    // *
    // * @param coordinate
    // * @return
    // * @throws FactoryException
    // * @throws TransformException
    // * @throws MismatchedDimensionException
    // */
    //public static Coordinate lonLatToXY(Coordinate coordinate)
    //    throws FactoryException, MismatchedDimensionException, TransformException {
    //    //Hints hint = new Hints(Hints.LENIENT_DATUM_SHIFT, true);
    //    //CoordinateReferenceSystem sourceCRS = crsFactory.createFromWKT(GCS_China_2000);
    //    //CoordinateReferenceSystem targetCRS = crsFactory.createFromWKT(Mercator_XY_WKT);
    //    //CoordinateOperationFactory coFactory = ReferencingFactoryFinder.getCoordinateOperationFactory(hint);
    //    //CoordinateOperation co = coFactory.createOperation(sourceCRS, targetCRS);
    //    //MathTransform transform = co.getMathTransform();
    //    //double x = coordinate.x;
    //    //double y = coordinate.y;
    //    //DirectPosition point = new GeneralDirectPosition(x, y);
    //    //point = transform.transform(point, point);
    //    //double lat = point.getOrdinate(0);
    //    //double lon = point.getOrdinate(1);
    //    //return new Coordinate(lat, lon);
    //
    //    //这里是以OGC WKT形式定义的是World Mercator投影，网页地图一般使用该投影
    //
    //    //CoordinateReferenceSystem crsTarget = CRS.parseWKT(strWKTMercator);
    //    CoordinateReferenceSystem crsTarget = CRS.parseWKT(china2000);
    //    //CoordinateReferenceSystem crsTarget = CRS.decode("EPSG:3857");
    //
    //    // 投影转换
    //    MathTransform transform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, crsTarget);
    //    return JTS.transform(coordinate, null, transform);
    //}

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {

            Coordinate coordinate1 = new Coordinate(117.181729,39.129627);
            Coordinate coordinate2 = new Coordinate(117.190722,39.128596);
            Coordinate coordinate3 = new Coordinate(120.002,30.002);

            GeometryFactory geometryFactory = new GeometryFactory();
            Point point1 = geometryFactory.createPoint(coordinate1);
            Point point2 = geometryFactory.createPoint(coordinate2);
            Point point3 = geometryFactory.createPoint(coordinate3);

            CoordinateReferenceSystem wgs84 = DefaultGeographicCRS.WGS84;
            CoordinateReferenceSystem albersNorthAsia = CRS.parseWKT(albers_north_asia);

            MathTransform transform = CRS.findMathTransform(wgs84, albersNorthAsia);

            Point targetPoint1 = (Point) JTS.transform(point1, transform);
            Point targetPoint2 = (Point) JTS.transform(point2, transform);
            Point targetPoint3 = (Point) JTS.transform(point3, transform);

            Coordinate[] coordinates = new Coordinate[2];
            coordinates[0] = targetPoint1.getCoordinate();
            coordinates[1] = targetPoint2.getCoordinate();

            Coordinate[] coordinates2 = new Coordinate[4];
            coordinates2[0] = targetPoint1.getCoordinate();
            coordinates2[1] = targetPoint2.getCoordinate();
            coordinates2[2] = targetPoint3.getCoordinate();
            coordinates2[3] = targetPoint1.getCoordinate();

            LineString lineString = geometryFactory.createLineString(coordinates);
            Polygon polygon = geometryFactory.createPolygon(coordinates2);

            System.out.println("直线距离："+lineString.getLength());
            System.out.println("albers 精准面积：" + polygon.getArea());

            ////CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326");
            //CoordinateReferenceSystem sourceCRS = DefaultGeographicCRS.WGS84;
            ////CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:3857");
            //CoordinateReferenceSystem targetCRS=CRS.parseWKT(china2000);
            //// CGCS2000 地理坐标系
            ////        CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:4490");
            //MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, true);
            //
            //GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
            //// PS:通常逻辑上理解经度应该是横坐标x，纬度是y，可是这里经度要填到y，纬度x，否则会报错
            //Coordinate coordinate = new Coordinate(30, 100);
            //Point source = geometryFactory.createPoint(coordinate);
            //Point dist = (Point)JTS.transform(source, transform);
            //System.out.println(dist.toString());
            //
            //List<Coordinate> points = new ArrayList<Coordinate>();
            //points.add(new Coordinate(120, 30));
            //points.add(new Coordinate(120.002, 30));
            //points.add(new Coordinate(120.002, 30.002));
            //points.add(new Coordinate(120, 30));
            ////创建面
            //Polygon polygon = GeometryUtil.createPolygon(points);
            ////获取面积和长度
            //System.out.println(
            //    "面积:" + new BigDecimal(Double.toString(GeometryUtil.getAreaLonLat(polygon))).toPlainString());

            ////创建geohash对象
            //GeoHash geoHash = GeoHash.withBitPrecision(34.288639,108.9132,30);
            //
            ////穿件geohash字符串
            //String str = GeoHash.geoHashStringWithCharacterPrecision(34.288639,108.9132,6);
            //
            ////获取geohash的base32编码
            //System.out.println(geoHash.toBase32());
            //
            //System.out.println(geoHash.toString());
            //
            //System.out.println(str);
            //
            ////获取邻近区块
            //GeoHash[] adjacent = geoHash.getAdjacent();
            //
            //List<GeoHash> list = Lists.newArrayList(adjacent);
            //list.add(geoHash);
            //System.out.println();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //
    ///***
    // * 拟合多项式系数
    // */
    //private static volatile double[] coefficient = new double[]{0.997069803864428,4.53372726430934E-4,-1.7587953894332355E-4,5.028882528864579E-7};
    //
    ///***
    // * 单位米
    // */
    //private final static double radius = 6378.137D * 1000;
    //
    //public static void main(String[] args) {
    //
    //    Coordinate coordinate1 = new Coordinate(120,30);
    //    Coordinate coordinate2 = new Coordinate(120.002,30);
    //    Coordinate coordinate3 = new Coordinate(120.002,30.002);
    //    Coordinate coordinate4 = new Coordinate(120,30.002);
    //    Coordinate coordinate5 = new Coordinate(120.001,30.001);
    //
    //    System.out.println(distHaversineRAD(coordinate1.x, coordinate1.y, coordinate2.x, coordinate2.y));
    //
    //    System.out.println(sphereDis(coordinate1.x, coordinate1.y, coordinate2.x, coordinate2.y));
    //
    //    System.out.println(calDistSameCityWithoutTrigo(coordinate1.x,coordinate1.y,coordinate2.x,coordinate2.y));
    //
    //    List<Coordinate> list = Lists.newArrayList(coordinate1, coordinate2, coordinate3, coordinate4, coordinate5);
    //
    //    System.out.println(computeArea(list));
    //}
    //
    //private static double computeArea(List<Coordinate> coordinateList){
    //
    //    if(coordinateList.size() < 3){
    //        return 0;
    //    }
    //    Coordinate o = coordinateList.get(0);
    //    double area = 0;
    //    for(int i = 1; i < coordinateList.size() - 1; i++){
    //        double oa = calDistSameCityWithoutTrigo(o.x, o.y, coordinateList.get(i).x, coordinateList.get(i).y);
    //        double ob = calDistSameCityWithoutTrigo(o.x, o.y, coordinateList.get(i + 1).x, coordinateList.get(i + 1).y);
    //        double sin = getSinAByCrossProduct(o, coordinateList.get(i), coordinateList.get(i + 1));
    //        area += 0.5 * oa * ob * sin;
    //    }
    //    return area;
    //}
    //
    ///***
    // * 根据叉乘返回sin，带正负符号
    // * 假设原点为O，坐标A (x1,y1)，坐标B (x2,y2) crossProduct = x1y2 - x2y1 = |OA| * |OB| * sin(AOB)
    // * sin(AOB) = (x1y2 - x2y1)/(|OA| * |OB|)
    // * @param o
    // * @param a
    // * @param b
    // * @return
    // */
    //private  static double getSinAByCrossProduct(Coordinate o, Coordinate a, Coordinate b){
    //    double oax = a.x - o.x;
    //    double oay = a.y - o.y;
    //    double obx = b.x - o.x;
    //    double oby = b.y - o.y;
    //
    //    double crossProduct = oax*oby - obx*oay;
    //
    //    double oaLen = Math.sqrt((a.x - o.x) * (a.x - o.x) + (a.y - o.y) * (a.y - o.y));
    //
    //    double obLen = Math.sqrt((b.x - o.x) * (b.x - o.x) + (b.y - o.y) * (b.y - o.y));
    //
    //    return crossProduct/(oaLen*obLen);
    //}
    //
    ///***
    // * 同城市去三角计算优化版本球面距离计算，1万米误差1米
    // * <link>https://tech.meituan.com/2014/09/05/lucene-distance.html</link>
    // * @param longitudeA
    // * @param latitudeA
    // * @param longitudeB
    // * @param latitudeB
    // * @return
    // */
    //private static  Double calDistSameCityWithoutTrigo(double longitudeA, double latitudeA, double longitudeB, double latitudeB){
    //
    //    double dx = longitudeA - longitudeB;
    //    double dy = latitudeA - latitudeB;
    //    double b = (latitudeA + latitudeB) / 2.0;
    //    // 东西距离
    //    double Lx = (coefficient[3] * b*b*b  + coefficient[2]* b*b  +coefficient[1] * b + coefficient[0] ) * radian(dx) * radius;
    //
    //    // 南北距离
    //    double Ly = radius * radian(dy);
    //    // 用平面的矩形对角距离公式计算总距离
    //    return Math.sqrt(Lx * Lx + Ly * Ly);
    //}
    //
    //private  static double radian(double angle) {
    //    return angle * Math.PI / 180.0;
    //}
    //
    //private  static double distHaversineRAD(double lon1, double lat1, double lon2, double lat2) {
    //
    //    lat1 = lat1 * Math.PI / 180.0;
    //    lon1 = lon1 * Math.PI / 180.0;
    //    lat2 = lat2 * Math.PI / 180.0;
    //    lon2 = lon2 * Math.PI / 180.0;
    //    double hsinX = Math.sin((lon1 - lon2) * 0.5);
    //    double hsinY = Math.sin((lat1 - lat2) * 0.5);
    //    double h = hsinY * hsinY +
    //        (Math.cos(lat1) * Math.cos(lat2) * hsinX * hsinX);
    //    return 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h)) * 6367000;
    //}
    //
    //private  static double sphereDis(double lon1, double lat1,double lon2, double lat2){
    //    lat1 = lat1 * Math.PI / 180.0;
    //    lat2 = lat2 * Math.PI / 180.0;
    //    lon1 = lon1 * Math.PI / 180.0;
    //    lon2 = lon2 * Math.PI / 180.0;
    //    return radius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
    //}
}