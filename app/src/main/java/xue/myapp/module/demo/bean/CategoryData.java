package xue.myapp.module.demo.bean;

import java.util.List;

import xue.myapp.common.bean.Rtn;

/**
 * 作者：薛
 * 时间：2017/5/9 14:57
 */
public class CategoryData extends Rtn {

    /**
     * error : false
     * results : [{"_id":"59101aa9421aa90c7a8b2ade","createdAt":"2017-05-08T15:13:45.831Z","desc":"泡泡效果的索引滚动控件","images":["http://img.gank.io/ca20efbb-7751-4584-adef-c04434448719"],"publishedAt":"2017-05-09T12:13:25.467Z","source":"web","type":"Android","url":"https://github.com/cdflynn/bubble-scroll","used":true,"who":"KNOX"},{"_id":"591091e4421aa90c83a513fe","createdAt":"2017-05-08T23:42:28.112Z","desc":"底部导航tab","images":["http://img.gank.io/2c9ce9b6-135c-4cf0-a50f-a0fb7d31a6c2"],"publishedAt":"2017-05-09T12:13:25.467Z","source":"chrome","type":"Android","url":"https://github.com/bufferapp/AdaptableBottomNavigation","used":true,"who":"Jason"},{"_id":"591108f3421aa90c7a8b2ae4","createdAt":"2017-05-09T08:10:27.504Z","desc":"理解 Android 源码编译命令","publishedAt":"2017-05-09T12:13:25.467Z","source":"chrome","type":"Android","url":"http://gityuan.com/2016/03/19/android-build/","used":true,"who":"Fei"},{"_id":"591139d1421aa90c7d49ad6c","createdAt":"2017-05-09T11:38:57.628Z","desc":"透明屏幕: 装逼利器","images":["http://img.gank.io/6d5fa20e-2ae0-4a02-a9c0-8af75a7cf2ea"],"publishedAt":"2017-05-09T12:13:25.467Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/54e6286a2e73","used":true,"who":null},{"_id":"590d2d00421aa90c83a513e8","createdAt":"2017-05-06T09:55:12.318Z","desc":"自己实现一个超轻量级 JVM ","publishedAt":"2017-05-08T11:22:01.540Z","source":"chrome","type":"Android","url":"https://www.codeproject.com/Articles/24029/Home-Made-Java-Virtual-Machine","used":true,"who":"daimajia"},{"_id":"590ef4c5421aa90c83a513f0","createdAt":"2017-05-07T18:19:49.997Z","desc":"Android SO 文件的兼容和适配","publishedAt":"2017-05-08T11:22:01.540Z","source":"web","type":"Android","url":"http://blog.coderclock.com/2017/05/07/android/Android-so-files-compatibility-and-adaptation/","used":true,"who":"D_clock"},{"_id":"590f4e77421aa90c7d49ad59","createdAt":"2017-05-08T00:42:31.109Z","desc":"优雅的Snackbar","images":["http://img.gank.io/c230ed80-2a3b-443a-a831-df9453519e94"],"publishedAt":"2017-05-08T11:22:01.540Z","source":"web","type":"Android","url":"https://github.com/TonnyL/Light","used":true,"who":"黎赵太郎"},{"_id":"590faecb421aa90c7a8b2ad5","createdAt":"2017-05-08T07:33:31.794Z","desc":"一款支持透明度的取色器，想取哪里点哪里   O(∩_∩)O","images":["http://img.gank.io/800aa39c-8c0f-4fff-96dd-892259362e0f"],"publishedAt":"2017-05-08T11:22:01.540Z","source":"web","type":"Android","url":"https://github.com/DingMouRen/ColorPicker","used":true,"who":null},{"_id":"590fdc0a421aa90c7a8b2ad7","createdAt":"2017-05-08T10:46:34.42Z","desc":"Android Java8 外置标准库~","publishedAt":"2017-05-08T11:22:01.540Z","source":"chrome","type":"Android","url":"https://github.com/retropiler/retropiler","used":true,"who":"代码家"},{"_id":"590fdc87421aa90c7a8b2ad8","createdAt":"2017-05-08T10:48:39.747Z","desc":"滑动式选择器，用在选头像，选背景图非常适合的场景。","images":["http://img.gank.io/ba5e1840-1ecf-4dc8-a0e9-d8b558d7a192"],"publishedAt":"2017-05-08T11:22:01.540Z","source":"chrome","type":"Android","url":"https://github.com/GoodieBag/CarouselPicker","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<Category> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Category> getResults() {
        return results;
    }

    public void setResults(List<Category> results) {
        this.results = results;
    }

    public static class Category {
        /**
         * _id : 59101aa9421aa90c7a8b2ade
         * createdAt : 2017-05-08T15:13:45.831Z
         * desc : 泡泡效果的索引滚动控件
         * images : ["http://img.gank.io/ca20efbb-7751-4584-adef-c04434448719"]
         * publishedAt : 2017-05-09T12:13:25.467Z
         * source : web
         * type : Android
         * url : https://github.com/cdflynn/bubble-scroll
         * used : true
         * who : KNOX
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
