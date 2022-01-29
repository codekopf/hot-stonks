//package com.codekopf.beststocksscrapper.config;
//
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//import org.apache.commons.io.FilenameUtils;
//
//public class StockConfig {
//
//    private List<ProductConfig> productConfigList;
//    private Map<String, ProductConfig> productConfigMap;
//
//    public List<ProductConfig> getProductConfigList() {
//        return productConfigList;
//    }
//
//    public void setProductConfigList(List<ProductConfig> productConfigList) {
//        this.productConfigList = productConfigList;
//    }
//
//    /**
//     * @return Map of product configs with filename without extension as a key
//     */
//    public Map<String, ProductConfig> getProductConfigMap() {
//        if (productConfigMap == null) {
//            productConfigMap = productConfigList.stream()
//                                                .collect(Collectors.toMap(productConfig -> FilenameUtils.removeExtension(productConfig.getSourceFile()), Function.identity()));
//        }
//        return productConfigMap;
//    }
//
//    public int getCleanProductId(int productId) {
//        for (ProductConfig productConfig : productConfigList) {
//            if (productConfig.getSpotId() == productId) {
//                return productConfig.getCleanSpotId();
//            } else if (productConfig.getForwardId() == productId) {
//                return productConfig.getCleanForwardId();
//            }
//        }
//        return 0;
//    }
//
//    public int getSpotIdForForwardId(int forwardId) {
//        for (ProductConfig productConfig : productConfigList) {
//            if (productConfig.getForwardId() == forwardId) {
//                return productConfig.getSpotId();
//            } else if (productConfig.getCleanForwardId() == forwardId) {
//                return productConfig.getCleanSpotId();
//            }
//        }
//        return 0;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .append("productConfigList", productConfigList)
//                .toString();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//
//        ProductsConfig that = (ProductsConfig) o;
//
//        return new EqualsBuilder()
//                .append(productConfigList, that.productConfigList)
//                .isEquals();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder(17, 37)
//                .append(productConfigList)
//                .toHashCode();
//    }
//
//    public static class ProductConfig {
//        private String sourceFile;
//        private int spotId;
//        private int forwardId;
//        private int cleanSpotId;
//        private int cleanForwardId;
//
//        public ProductConfig() {
//        }
//
//        public String getSourceFile() {
//            return sourceFile;
//        }
//
//        public void setSourceFile(String sourceFile) {
//            this.sourceFile = sourceFile;
//        }
//
//        public int getSpotId() {
//            return spotId;
//        }
//
//        public void setSpotId(int spotId) {
//            this.spotId = spotId;
//        }
//
//        public int getForwardId() {
//            return forwardId;
//        }
//
//        public void setForwardId(int forwardId) {
//            this.forwardId = forwardId;
//        }
//
//        public int getCleanSpotId() {
//            return cleanSpotId;
//        }
//
//        public void setCleanSpotId(int cleanSpotId) {
//            this.cleanSpotId = cleanSpotId;
//        }
//
//        public int getCleanForwardId() {
//            return cleanForwardId;
//        }
//
//        public void setCleanForwardId(int cleanForwardId) {
//            this.cleanForwardId = cleanForwardId;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) {
//                return true;
//            }
//
//            if (o == null || getClass() != o.getClass()) {
//                return false;
//            }
//
//            ProductConfig that = (ProductConfig) o;
//
//            return new EqualsBuilder()
//                    .append(spotId, that.spotId)
//                    .append(forwardId, that.forwardId)
//                    .append(cleanSpotId, that.cleanSpotId)
//                    .append(cleanForwardId, that.cleanForwardId)
//                    .append(sourceFile, that.sourceFile)
//                    .isEquals();
//        }
//
//        @Override
//        public int hashCode() {
//            return new HashCodeBuilder(17, 37)
//                    .append(sourceFile)
//                    .append(spotId)
//                    .append(forwardId)
//                    .append(cleanSpotId)
//                    .append(cleanForwardId)
//                    .toHashCode();
//        }
//
//        @Override
//        public String toString() {
//            return new ToStringBuilder(this)
//                    .append("sourceFile", sourceFile)
//                    .append("spotId", spotId)
//                    .append("forwardId", forwardId)
//                    .append("cleanSpotId", cleanSpotId)
//                    .append("cleanForwardId", cleanForwardId)
//                    .toString();
//        }
//    }
//}
