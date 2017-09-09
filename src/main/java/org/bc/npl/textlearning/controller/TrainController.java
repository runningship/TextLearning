package org.bc.npl.textlearning.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.bc.npl.textlearning.entity.Gravity;
import org.bc.npl.textlearning.entity.Word;
import org.bc.npl.textlearning.service.GravityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.aop.annotation.PublicMethod;
import com.jumore.dove.boot.comp.DAO;
import com.jumore.dove.plugin.Page;
import com.jumore.dove.util.ParamMap;

@PublicMethod
@Controller
@RequestMapping(value="")
public class TrainController {

    @Autowired
    GravityService gravityService;
    
    private String interpunctionStr = "。，、；：？！…―ˉˇ〃‘　'“”[]々～‖∶＂＇｀｜〔〕〈〉《》「」『』．〖〗【】（）()［］｛｝";
    
    private static List<String> words = null;
    
    @RequestMapping(value="addTrain")
    public ModelAndView addTrain(){
        Gravity po = DAO.get(Gravity.class, 560L);
        po.getNext().codePointAt(0);
        return new ModelAndView();
    }
    
    @PublicMethod
    @ResponseBody
    @RequestMapping(value="doAddTrain")
    public String doAddTrain(String text){
        if(words==null){
            inti();
        }
        try {
            text = IOUtils.toString(this.getClass().getResourceAsStream("/train/train.txt"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(text==null){
            return "";
        }
        if(text.length()<=1){
            return "";
        }
        for(int i=0;i<text.length()-2;i++){
            char ch1 = text.charAt(i);
            char ch2 = text.charAt(i+1);
            char ch3 = text.charAt(i+2);
            if(isInterpunction(ch1) || isInterpunction(ch2)){
                continue;
            }
            if(isWhiteSpace(ch1) || isWhiteSpace(ch2)){
                continue;
            }
            // 先单字判断
            if(isWordExsit(String.valueOf(ch1))){
                continue;
            }
            // 判断双字
            if(isWordExsit(String.valueOf(ch1)+String.valueOf(ch2))){
                i++;
                continue;
            }else{
                // 判断后面两个字的吸引力
                if(isInterpunction(ch3) || isWhiteSpace(ch3)){
                    //什么也不做
                }else{
                    Word vo = new Word();
                    vo.setText(String.valueOf(ch2)+String.valueOf(ch3));
                    Word pox = (Word) DAO.getByExample(vo);
                    if(pox!=null){
                        gravityService.addWord(String.valueOf(ch1) , "");
                        continue;
                    }
                }
            }
            
            
            gravityService.addWord(String.valueOf(ch1) , String.valueOf(ch2));
        }
        return "hello";
    }
    
    private boolean isWordExsit(String text) {
        Word word = new Word();
        word.setText(text);
        Word po = (Word) DAO.getByExample(word);
        if(po==null){
            return false;
        }
        return true;
    }

    @PublicMethod
    @ResponseBody
    @RequestMapping(value="think")
    public String think(){
        Page<Gravity> page = new Page<Gravity>();
        page.setPageSize(3);
        ParamMap pm = new ParamMap();
        pm.addOrder("score", "desc");
        page = DAO.findPageByParams(Gravity.class, page, "Gravity.listGravity", pm);
        for(Gravity g : page.result){
            String text = g.getWord()+g.getNext();
            Word word = new Word();
            word.setText(text);
            Object po = DAO.getByExample(word);
            if(po==null){
                DAO.save(word);
            }
        }
        DAO.execute("Gravity.clear", new ParamMap());
        return "thinking";
    }
    
    private boolean isInterpunction(char ch){
        "[]".indexOf(ch);
        return interpunctionStr.indexOf(ch)>-1;
    }
    
    private boolean isWhiteSpace(char ch){
        if((int)ch==13 || (int)ch==10){
            return true;
        }
        if(ch == '\r' || ch=='\n'){
            return true;
        }
        return ch==' ';
    }
    
    private void inti(){
        Word word = new Word();
        List<Word> list = DAO.listByExample(word);
        words = new ArrayList<String>();
        for(Word w : list){
            words.add(w.getText());
        }
    }
}