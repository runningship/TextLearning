package org.bc.npl.textlearning.service;

import java.util.HashMap;
import java.util.Map;

import org.bc.npl.textlearning.entity.Gravity;

import com.jumore.dove.boot.comp.DAO;

public class GravityService{
    
    private Map<String, Integer> trainMap = new HashMap<String , Integer>();
    
    public void addWord(String word , String next){
        Gravity gravity = new Gravity();
        gravity.setWord(word);
        gravity.setNext(next);
        Gravity po = (Gravity) DAO.getByExample(gravity);
        String text = word+next;
        if(trainMap.containsKey(text)){
           Integer score = trainMap.get(text);
           score++;
           trainMap.put(text, score);
        }else{
            trainMap.put(text, 1);
        }
//        if(po==null){
//            gravity.setScore(1);
//            DAO.save(gravity);
//        }else{
//            po.setScore(po.getScore()+1);
//            DAO.update(po);
//        }
    }
    
    public void startTrain(){
        trainMap.clear();
    }
    
    public void endTrain(){
        // 排序
    }
}
