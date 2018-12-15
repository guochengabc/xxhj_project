package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-17.
 * 说明:
 */
public class GetProductList extends RBResponse {

    /**
     * Name : 智能流量计
     * Description : 要多智能，有多智能！
     * Type : A
     * Price : 1000
     * CreateTime : 2016-09-12 15:41:35.107
     * PhotoUrl : images/special/2016/09/12/89e535a3977e40fcaf9c98da93c54fd9.jpg
     * ProductId : bf065bdb502949e68daf3aef506524eb
     */

    private List<Product> resobj;

    public List<Product> getResobj() {
        return resobj;
    }

    public void setResobj(List<Product> resobj) {
        this.resobj = resobj;
    }

    public static class Product {
        private String Name;
        private String Description;
        private String Type;
        private String Price;
        private String CreateTime;
        private String PhotoUrl;
        private String ProductId;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getPhotoUrl() {
            return PhotoUrl;
        }

        public void setPhotoUrl(String PhotoUrl) {
            this.PhotoUrl = PhotoUrl;
        }

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String ProductId) {
            this.ProductId = ProductId;
        }
    }
}
