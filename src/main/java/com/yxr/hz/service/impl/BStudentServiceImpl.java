package com.yxr.hz.service.impl;

import com.yxr.hz.dao.BStudentDao;
import com.yxr.hz.dao.RoomDao;
import com.yxr.hz.entity.BStudent;
import com.yxr.hz.service.BStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class BStudentServiceImpl implements BStudentService {
    @Autowired
    private BStudentDao bStudentDao;
    @Autowired
    private RoomDao roomDao;
    @Override
    public void insert(BStudent student) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now=df.format(new Date());
        student.setDate(now);
        bStudentDao.insert(student);
    }

    @Override
    public void update(BStudent student) {
        bStudentDao.update(student);
    }

    @Override
    public List<BStudent> findAll() throws ParseException {
        List<BStudent> list=bStudentDao.findAll();
        for(BStudent bStudent:list){
            bStudent.setRoom(roomDao.getById(bStudent.getRid()));
        }
        return list;
    }

    @Override
    public void delete(Integer id) {
        bStudentDao.delete(id);
    }

    @Override
    public List<BStudent> selectByName(String name) throws ParseException {
        return bStudentDao.findByName(name);
    }

    @Override
    public BStudent selectById(Integer id) throws ParseException {
        return bStudentDao.findById(id);
    }

    @Override
    public List<BStudent> selectByRid(Integer rid) {
        return bStudentDao.findByRid(rid);
    }

    @Override
    public void updateState(Integer id) {

    }
}
