package com.train.prac.enumeration;

public enum  Colors3 {
    RED{
        @Override
        public String getColor() {
            return "红色";
        }
    },BLUE{
        @Override
        public String getColor() {
            return "蓝色";
        }
    },GREEN{
        @Override
        public String getColor() {
            return "绿色";
        }
    };

    public abstract String getColor();
}
