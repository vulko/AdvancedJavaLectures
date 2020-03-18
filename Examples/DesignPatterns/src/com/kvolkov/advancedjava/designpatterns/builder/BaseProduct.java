package com.kvolkov.advancedjava.designpatterns.builder;

public class BaseProduct {

    public static class Builder {

        private String mName = "";
        private String mDescription;
        private Float mPrice = Float.NaN;

        public BaseProduct build() {
            return new BaseProduct(mName, mDescription, mPrice);
        }

        public Builder setName(String name) {
            this.mName = name;
            return this;
        }

        public Builder setDescription(String desc) {
            this.mDescription = desc;
            return this;
        }

        public Builder setPrice(Float price) {
            this.mPrice = price;
            return this;
        }

    }

    private final String mName;
    private final String mDescription;
    private final Float mPrice;

    private BaseProduct(String mName, String mDescription, Float mPrice) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mPrice = mPrice;
    }

    public String toString() {
        return mName + "(" + mDescription + ")" + "  price = " + mPrice;
    }
}
