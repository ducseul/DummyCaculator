/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eval;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author hacke
 */
public class Eval {

    /**
     * @param args the command line arguments
     */
    static String expr = "(5+((8-4)*(2+23)))*(13*24)";

    public static void main(String[] args) {
        // TODO code application logic here
        recurs(expr);
        System.out.println("Output: " + caculator(expr));
    }

    public static String recurs(String exp) {
        ArrayList<EntryLv> listEntry = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') {
                ++count;
                listEntry.add(new EntryLv(count, i));
            }
            if (exp.charAt(i) == ')') {
                --count;
                listEntry.add(new EntryLv(count, i));
            }
        }

        //set count is max level
        //count = 0
        for (EntryLv entryLv : listEntry) {
            System.out.println(entryLv);
            if (count < entryLv.level) {
                count = entryLv.level;
            }
        }

        for (int j = 0; j < listEntry.size(); j++) {
            if (listEntry.get(j).level == count) {
                String evaluating = exp.substring(listEntry.get(j).index, listEntry.get(j + 1).index + 1);
                int tmp = caculator(evaluating);
//                System.out.println("Caculated on: "+evaluating);
                StringBuilder sb = new StringBuilder(exp);
                sb.replace(listEntry.get(j).index, listEntry.get(j + 1).index + 1, String.valueOf(tmp));
                System.out.println("News: " + sb.toString());
                expr = sb.toString();
                recurs(sb.toString());
                break;
            }
        }
        return null;
    }

    //a+b; a*b; a/c
    public static int caculator(String str) {
        try {
            String tmp = "";
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != '(' && str.charAt(i) != ')') {
                    tmp += str.charAt(i);
                }
            }
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            Object result = engine.eval(tmp);
            return (int) result;
        } catch (ScriptException ex) {
            Logger.getLogger(Eval.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
