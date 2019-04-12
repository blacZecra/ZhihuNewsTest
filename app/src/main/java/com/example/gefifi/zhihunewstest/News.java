package com.example.gefifi.zhihunewstest;

import java.util.List;

/**
 * Created by gefifi on 2018/12/3.
 */

public class News {
    /**
     * {
     "date": "20190410",
     "stories": [{
     "images": ["https:\/\/pic1.zhimg.com\/v2-117ca4af22ff97be3a5585fa203f5ec8.jpg"],
     "type": 0,
     "id": 9710069,
     "ga_prefix": "041018",
     "title": "足坛有哪些很少人知道的冷知识？"
     },...,.]
     }

     */
    private String date;
    private List<StoryBean> stories;

    private List<StoryBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoryBean> getStories() {
        return stories;
    }

    public void setStories(List<StoryBean> stories) {
        this.stories = stories;
    }

    public List<StoryBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<StoryBean> top_stories) {
        this.top_stories = top_stories;
    }

    public void show(){
        System.out.println(stories.get(0).getTitle());
    }

    public static class StoryBean {
        List<String> images;
        String image;
        String type;
        String id;
        String ga_prefix;
        String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
