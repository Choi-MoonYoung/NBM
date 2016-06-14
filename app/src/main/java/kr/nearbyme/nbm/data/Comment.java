package kr.nearbyme.nbm.data;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 19..
 */
public class Comment implements Rdata {
    public String cmt_id;
    public String cmt_regDate;
    public String cmt_writerId;
    public String cmt_content;
    public String cmt_writerName;
    public int mine;

    public int getMine() {
        return mine;
    }

    public void setMine(int mine) {
        this.mine = mine;
    }

    public String getCmt_writerName() {
        return cmt_writerName;
    }

    public void setCmt_writerName(String cmt_writerName) {
        this.cmt_writerName = cmt_writerName;
    }

    public String getCmt_id() {
        return cmt_id;
    }

    public void setCmt_id(String cmt_id) {
        this.cmt_id = cmt_id;
    }

    public String getCmt_regDate() {
        return cmt_regDate;
    }

    public void setCmt_regDate(String cmt_regDate) {
        this.cmt_regDate = cmt_regDate;
    }

    public String getCmt_writerId() {
        return cmt_writerId;
    }

    public void setCmt_writerId(String cmt_writerId) {
        this.cmt_writerId = cmt_writerId;
    }

    public String getCmt_content() {
        return cmt_content;
    }

    public void setCmt_content(String cmt_content) {
        this.cmt_content = cmt_content;
    }
}
