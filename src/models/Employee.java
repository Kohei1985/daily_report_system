package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "employees")
@NamedQueries({     //SELECT文を入れる
    @NamedQuery(
            name = "getAllEmployees", //全ての従業員情報を取得する
            query = "SELECT e FROM Employee AS e ORDER BY e.id DESC"    //SELECT文
            ),
    @NamedQuery(
            name = "getAllEmployeeCount",   //従業員情報の全件数を取得
            query = "SELECT COUNT(e) FROM Employee AS e"    //SELECT文
            ),
    @NamedQuery(
            name = "checkRegisteredCode",   //指定された社員番号がすでにデータベースに存在しているか調べる
            query = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :code"   //SELECT文
            ),
    @NamedQuery(
            name = "checkLoginCodeAndPassword",     //従業員がログインするときに社員番号とパスワードが正しいかどうかチェックする
            query = "SELECT e FROM Employee AS e WHERE e.delete_flag = 0 AND e.code = :code AND e.password = :pass" //SELECT文
            ),

})
@Entity
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true) //このunique = trueはすでに存在している社員番号を登録できない旨をデータベースに伝えるもの（＝一意制約）
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", length = 64, nullable = false)//length=64はSHA256というハッシュ関数は64文字の文字列に変換するからここで指定
    private String password;

    @Column(name = "admin_flag", nullable = false)
    private Integer admin_frag;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    //getter/setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAdmin_frag() {
        return admin_frag;
    }

    public void setAdmin_frag(Integer admin_frag) {
        this.admin_frag = admin_frag;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }

}
