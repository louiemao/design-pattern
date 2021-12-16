package com.louie.study.dp.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 苍韧
 * @date 2021/5/12
 */
public class TestClient {
    public static void main(String[] args) {
        try {
            //computeCapityPlan();

            //printWarehouse();

            Object obj=null;
            String s = JSON.toJSONString(obj);

            System.out.println();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printWarehouse() {
        Map<String, List<String>> warehousePredictMap = Maps.newHashMap();
        warehousePredictMap.put("77", Lists.newArrayList("99", "B2CTJ"));
        warehousePredictMap.put("W012", Lists.newArrayList("113"));
        warehousePredictMap.put("105", Lists.newArrayList("B2CCD", "B2CCQ"));
        warehousePredictMap.put("W016", Lists.newArrayList("B2CCDLD", "B2CCQLD"));
        warehousePredictMap.put("W014", Lists.newArrayList("115", "116"));
        warehousePredictMap.put("120", Lists.newArrayList("88", "104", "B2CNB", "B2CZJFH"));
        warehousePredictMap.put("88",
            Lists.newArrayList("B2CSH-2-NEW", "B2CJSFH-3", "B2CSKT", "B2CNT", "B2CWX", "B2CJSFH-1"));
        warehousePredictMap.put("W011",
            Lists.newArrayList("111", "114", "B2CSKLD", "B2CNTLD", "B2CNBLD", "B2CWXLD", "B2CHDLDFH"));
        warehousePredictMap.put("101", Lists.newArrayList("103", "102"));
        warehousePredictMap.put("110", Lists.newArrayList("B2CWH"));
        warehousePredictMap.put("W017", Lists.newArrayList("B2CWHLD"));
        warehousePredictMap.put("106", Lists.newArrayList("B2CXA"));
        warehousePredictMap.put("W015", Lists.newArrayList("B2CXALD"));
        warehousePredictMap.put("NB32Z0001", Lists.newArrayList("B2CNJ"));
        warehousePredictMap.put("NB32Z0002", Lists.newArrayList("B2CNJLD"));
        System.out.println(JSON.toJSONString(warehousePredictMap));
    }

    private static void computeCapityPlan() {
        Integer stdLoadingQuantity = 4327;
        Integer hisLoadingQuantity = 4290;
        // 装载货量 = MAX(标准装载货量，历史装载货量)
        int loadingQuantity = Math.max(stdLoadingQuantity, hisLoadingQuantity);

        Double stdRerunRate = 2.09D;
        Double hisRerunRate = 2.04D;
        // 复跑率 = MAX(标准复跑率，历史复跑率)
        double rerunRate = Math.max(stdRerunRate, hisRerunRate);

        Integer quantity = 235094;
        BigDecimal floatingFactor = new BigDecimal("1");

        // 货量*浮动系数 / 装载货量 = 装载趟数
        BigDecimal loadingCount = BigDecimal.valueOf(quantity).multiply(floatingFactor)
            .divide(BigDecimal.valueOf(loadingQuantity), 6, RoundingMode.HALF_UP);

        // 装载趟数 / 复跑率 = 车辆数
        BigDecimal carCount = loadingCount.divide(BigDecimal.valueOf(rerunRate), 6, RoundingMode.HALF_UP);

        //历史车型
        Map<String, Integer> hisCarModelRatio = Maps.newHashMap();
        hisCarModelRatio.put("20180618112047289546", 170);
        hisCarModelRatio.put("Mode11", 1);
        hisCarModelRatio.put("Mode2", 10);

        // 历史车型总趟数
        BigDecimal totalCarCount = BigDecimal.valueOf(
            hisCarModelRatio.values().stream().reduce(0, Integer::sum));

        Map<String, Integer> carModelQuantity = Maps.newHashMap();
        hisCarModelRatio.forEach((carModelCode, count) -> {
            // 车型需求数量 = 车辆数 * （历史车型趟数 / 历史车型总趟数）
            BigDecimal carModelCount = carCount.multiply(BigDecimal.valueOf(count))
                .divide(totalCarCount, 0, RoundingMode.HALF_UP);
            carModelQuantity.put(carModelCode, carModelCount.intValue());
        });

        System.out.println(carModelQuantity);
    }

    public static class TestBO {
        private String testA;
        private String testB;

        public String getTestA() {
            return testA;
        }

        public void setTestA(String testA) {
            this.testA = testA;
        }

        public String getTestB() {
            return testB;
        }

        public void setTestB(String testB) {
            this.testB = testB;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }
            TestBO testBO = (TestBO)o;
            return Objects.equals(testA, testBO.testA) && Objects.equals(testB, testBO.testB);
        }

        @Override
        public int hashCode() {
            return Objects.hash(testA, testB);
        }
    }
}
