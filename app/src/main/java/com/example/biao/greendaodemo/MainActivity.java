package com.example.biao.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.biao.greendao.DaoMaster;
import com.example.biao.greendao.DaoSession;
import com.example.biao.greendao.UaerDao;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private UaerDao userDao;
    private long insertId = -1;
    private RecyclerView recyclerView;
    private Button insert, query, update, delete;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Uaer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "my-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUaerDao();

        recyclerView = findViewById(R.id.recyclerview);
        insert = findViewById(R.id.insert);
        query = findViewById(R.id.query);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        list = userDao.loadAll();


        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        insert.setOnClickListener(this);
        query.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

    }


    /**
     * 插入数据
     */
    private void insert() {
        insertId = -1;
        Uaer uaer = new Uaer(null, "zhangsan");
        insertId = userDao.insert(uaer);
        if (insertId != -1) {
            Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "插入失败", Toast.LENGTH_SHORT).show();
        }
        if (list != null) {
            list.clear();
        }

        list.addAll(userDao.loadAll());
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(list.size() - 1);
    }

    /**
     * 查询数据
     */
    private void query(){
        Uaer uaer = null;
        if(insertId != -1){
            uaer = userDao.queryBuilder().where(UaerDao.Properties.Id.eq(insertId)).build().unique();
        }
        if(uaer != null){
            list.clear();
            list.add(uaer);
            recyclerViewAdapter.notifyDataSetChanged();
            Toast.makeText(this,"查询成功",Toast.LENGTH_SHORT).show();
        }else{
            list.clear();
            recyclerViewAdapter.notifyDataSetChanged();
            Toast.makeText(this,"用户不存在",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 更新数据
     */
    private void update(){
        Uaer uaer = null;
        if(insertId != -1){
            uaer = userDao.queryBuilder().where(UaerDao.Properties.Id.eq(insertId)).build().unique();
        }
        if(uaer != null){
            uaer.setName("lisi");
            userDao.update(uaer);
            list.addAll(userDao.loadAll());
            recyclerViewAdapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(list.size() - 1);
            if(userDao.queryBuilder().where(UaerDao.Properties.Id.eq(insertId)).build().unique().getName().equals("lisi")){
                Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"修改失败",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"用户不存在",Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 删除数据
     */
    private void delete(){
        Uaer uaer = null;
        if(insertId != -1){
            uaer = userDao.queryBuilder().where(UaerDao.Properties.Id.eq(insertId)).build().unique();
        }
        if(uaer != null){
            userDao.delete(uaer);
            list.addAll(userDao.loadAll());
            recyclerViewAdapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(list.size() - 1);
            if(userDao.queryBuilder().where(UaerDao.Properties.Id.eq(insertId)).build().unique() == null){
                Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"删除失败",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"用户不存在",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert:
                insert();
                break;
            case R.id.query:
                query();
                break;
            case R.id.update:
                update();
                break;
            case R.id.delete:
                delete();
                break;
            default:
                break;
        }
    }
}
