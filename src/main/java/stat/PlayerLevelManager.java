package stat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class PlayerLevelManager extends JavaPlugin {

    public int level;
    public int xp;
    public String getxp;
    public String getlevel;

    public asdfasf(int level, int xp) {
        this.level = level;
        this.xp = xp;
    }
    
    //저장
    public void savexp(){
         //플레이어의 이름으로 저장 하고싶은데 뭔 이밴트로 xp가 올라가는지 몰라
    	Properties p = new Properties();
         File dir = new File(".\\xp\\");
         File playerxp = new File(".\\xp\\"/*+플레이어 이름*/+"-xp.properties");
         //만약에 xp 폴더가 없다면
         if(!dir.isDirectory()){
             try {
            	 //생성
				FileWriter writer = new FileWriter(".\\xp\\");
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }else {
         //만약 플레이어의 설정파일이 있다면
         if (playerxp.isDirectory()){
			try {
				FileInputStream in = new FileInputStream(/*플레이어 이름*/"-xp.properties");
				try {
					p.load(in);
					in.close();
				} catch (IOException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
             getxp = p.getProperty("xp");
             getlevel = p.getProperty("level");
             
             int cxp = Integer.valueOf(getxp)+xp;
             int clevel = Integer.valueOf(getlevel)+level;
             //출력
             p.setProperty("xp", String.valueOf(cxp));
             p.setProperty("level",String.valueOf(clevel));
    		try {
    			FileOutputStream out = new FileOutputStream(".\\xp\\"+/*플레이어 이름+*/"-xp.properties");
    			try {
    				p.store(out,"Player Xp Setting");
    			} catch (IOException e) {
    				getLogger().warning("IO Exception Error Code:");
        			getLogger().warning(e.toString());
    				e.printStackTrace();
    			}
    		} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			getLogger().warning("File Not Found Error Code:");
    			getLogger().warning(e1.toString());
    			e1.printStackTrace();
    		}
         

         }else {
        	 //없다면
        	 p.setProperty("xp", String.valueOf(xp));
             p.setProperty("level",String.valueOf(level));
             FileOutputStream out;
    		try {
    			out = new FileOutputStream(".\\xp\\"+/*플레이어 이름+*/"-xp.properties");
    			try {
    				p.store(out,"Player Xp Setting");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
         }
         }
    }




    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    private void xpcheck(Player player, asdfasf playerLevelManager) {
        int xpneeded = this.getConfig().getInt("Levels.1.xp");
        int xp = playerLevelManager.getXp();

        if (xp  >=xpneeded ) {
            player.sendMessage("§6Leveled UP!");
            playerLevelManager.setLevel(1);
        }
    }

}
