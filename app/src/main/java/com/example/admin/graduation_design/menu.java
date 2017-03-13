package com.example.admin.graduation_design;

/**
 * Created by admin on 2016/10/17.
 */
public class menu {

        private int number;
        private String name;
        private String price;
        public int getNumber(){
            return  number;
        }
        public void setNumber(){
            this.number = number;
        }
        public String getName(){
            return  name;
        }
        public void setName(){
            this.name = name;
        }
        public String getPrice(){
            return price;
        }
        public void setPrice(){
            this.price = price;
        }
        public menu(){
            super();
            this.number = number;
            this.name = name;
            this.price = price;
        }

    @Override
    public String toString() {
        return "menu{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

