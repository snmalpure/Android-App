package com.example.pictinsights;
    public class NoticeUpdate {
        String news;
        String newsId;
        String date;

        public NoticeUpdate() {
        }

        public NoticeUpdate(String news, String newsId, String date) {
            this.news = news;
            this.newsId = newsId;
            this.date=date;
        }

        public String getNews() {
            return news;
        }

        public String getNewsId() {
            return newsId;
        }

        public String getDate() {
            return date;
        }
    }

