package Servicess;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Media.*;

public class operationsService {
    private static  operationsService instance;
    private Connection dataBase;
    private operationsService() {
        try {
            OracleDataSource obs = new OracleDataSource();
            obs.setURL(DataBaseDetails.getConnLink());
            obs.setUser(DataBaseDetails.getUser());
            obs.setPassword(DataBaseDetails.getPass());
            dataBase = obs.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized operationsService getInstance() {
        if (instance == null) {
            instance = new operationsService();
        }
        return instance;
    }

    public List<Media> getMediaByName(String name) {
        List<Media> data = new ArrayList<Media>();
        try {
            name = name.toLowerCase();
            String sql = "SELECT * FROM media WHERE LOWER(name) LIKE \'%" + name + "%\'";
            PreparedStatement st = dataBase.prepareStatement(sql);
            ResultSet res = st.executeQuery();

            while (res.next()) {
                data.add(Media.parse(res));
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private Media getMediaById(int id) {
        CRUD<Media> cr = CRUD.getInstance();
        return cr.readByID(Media.class, id);
    }

    public Media getDetailedInfo(int id) {
        Media md = getMediaById(id);
        if(md == null) {
            return null;
        }

        if(md.getCategory() == null) {
            return md;
        }

        if(md.getCategory().equals("anime")) {
            CRUD<Anime> cr = CRUD.getInstance();
            return cr.readByID(Anime.class, id);
        }

        if(md.getCategory().equals("novel")) {
            CRUD<Novel> cr = CRUD.getInstance();
            return cr.readByID(Novel.class, id);
        }

        if(md.getCategory().equals("manga")) {
            CRUD<Manga> cr = CRUD.getInstance();
            return cr.readByID(Manga.class, id);
        }

        return md;
    }

    public List<Episode> getEpisodes(int id_anime) {
        List<Episode> data = new ArrayList<Episode>();
        try {
            String sql = "SELECT * FROM Episode WHERE id_anime = " + String.valueOf(id_anime);
            PreparedStatement st = dataBase.prepareStatement(sql);
            ResultSet res = st.executeQuery();

            while (res.next()) {
                data.add(Episode.parse(res));
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<Chapter> getChapter(int id_manga) {
        List<Chapter> data = new ArrayList<Chapter>();
        try {
            String sql = "SELECT * FROM chapter WHERE id_manga = " + String.valueOf(id_manga);
            PreparedStatement st = dataBase.prepareStatement(sql);
            ResultSet res = st.executeQuery();

            while (res.next()) {
                data.add(Chapter.parse(res));
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<Volume> getVolumes(int id_novel) {
        List<Volume> data = new ArrayList<Volume>();
        try {
            String sql = "SELECT * FROM Volume WHERE id_novel = " + String.valueOf(id_novel);
            PreparedStatement st = dataBase.prepareStatement(sql);
            ResultSet res = st.executeQuery();

            while (res.next()) {
                data.add(Volume.parse(res));
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public Review getReview(int id_user, int id_media) {
        String sql = "SELECT * FROM review WHERE id_user = " + String.valueOf(id_user) +
                " AND id_media = " + String.valueOf(id_media);

        try {
            PreparedStatement st = dataBase.prepareStatement(sql);
            ResultSet res = st.executeQuery();

            if(res.next()) {
                return Review.parse(res);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double getRating(Media md) {
        try {
            String sql = "SELECT NVL(AVG(rating), 1)" +
                          "FROM REVIEW WHERE id_media = " + String.valueOf(md.getId());

            PreparedStatement st = dataBase.prepareStatement(sql);
            ResultSet res = st.executeQuery();

            if(res.next()) {
                return res.getDouble(1);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return 1.0;
    }

    public void ratingUpdate(Media md) {
        md = new Media(md);
        Media aux = new Media(md);
        aux.setRating(getRating(md));
        CRUD<Media> cr = CRUD.getInstance();
        cr.update(md, aux);
    }

    public List<Review> getReviewList(int id_user) {
        List<Review> data = new ArrayList<Review>();
        try {
            String sql = "SELECT * FROM Review WHERE id_user = " + String.valueOf(id_user);
            PreparedStatement st = dataBase.prepareStatement(sql);
            ResultSet res = st.executeQuery();

            while (res.next()) {
                data.add(Review.parse(res));
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<WatchList> getWatchList(int id_user) {
        List<WatchList> data = new ArrayList<WatchList>();
        try {
            String sql = "SELECT * FROM WatchList WHERE id_user = " + String.valueOf(id_user);
            PreparedStatement st = dataBase.prepareStatement(sql);
            ResultSet res = st.executeQuery();

            while (res.next()) {
                data.add(WatchList.parse(res));
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public WatchList getWatchListItem(int id_user, int id_media) {
        String sql = "SELECT * FROM watchlist WHERE id_user = " + String.valueOf(id_user) +
                " AND id_media = " + String.valueOf(id_media);
        try {
            PreparedStatement st = dataBase.prepareStatement(sql);
            ResultSet res = st.executeQuery();

            if(res.next()) {
                return WatchList.parse(res);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
