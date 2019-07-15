package com.yun.hai.effctivetest.staticbuilder;

/**
 * 只有在属性多，且大部分属性可选的情况下考虑使用构造器
 * 比javabean的方式要安全
 */
public class House {
    private String basis = null;
    private String top = null;
    private String line = null;
    private String fence = null;

    @Override
    public String toString() {
        return "House{" +
                "basis='" + basis + '\'' +
                ", top='" + top + '\'' +
                ", line='" + line + '\'' +
                ", fence='" + fence + '\'' +
                '}';
    }

    private House(Builder builder) {
        this.basis = builder.basis;
        this.top = builder.top;
        this.line = builder.line;
        this.fence = builder.fence;

    }


    public static class Builder {
        private String basis = null;
        private String top = null;
        private String line = null;
        private String fence = null;


        public Builder buildBasis(String basis) {
            this.basis = basis;
            return this;
        }

        public Builder buildTop(String top) {
            this.top = top;
            return this;
        }

        public Builder buildLine(String line) {
            this.line = line;
            return this;
        }

        public Builder buildFence(String fence) {
            this.fence = fence;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }

}
