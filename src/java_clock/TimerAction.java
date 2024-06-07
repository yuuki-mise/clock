package java_clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * 
 */

public class TimerAction extends Object {

    private LocalDateTime timerTime;
    private Timer timer;

    public void perform(){

        // ウィンドウ（フレーム）を生成する
        JFrame mainFrame = new JFrame("Timer");
        // 閉じるボタンを押したときに閉じる
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ウィンドウ（フレーム）のサイズを設定する
        mainFrame.setSize(1200, 500);
        // ウィンドウ（フレーム）を真ん中に表示する
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new GridLayout(2, 1));
    
        // ラベルを追加する
        JLabel dateLabel1 = new JLabel();
        dateLabel1.setText("");
        dateLabel1.setForeground(Color.BLACK);
        // フォントのスタイルをPLAINに設定
        dateLabel1.setFont(new Font("Consolas", Font.PLAIN, 60));
        mainFrame.add(dateLabel1);

        // ラベルを追加する
        JLabel dateLabel2 = new JLabel();
        dateLabel2.setText("");
        dateLabel2.setForeground(Color.BLACK);
        // フォントのスタイルをPLAINに設定
        dateLabel2.setFont(new Font("Consolas", Font.PLAIN, 60));
        mainFrame.add(dateLabel2);
        
        // ボタンを追加する
        JButton threeMinTimerButton = new JButton("タイマー 3分");
        JButton fiveMinTimerButton = new JButton("タイマー 5分");


        // ボタンが押されたときのアクション
        threeMinTimerButton.addActionListener(new ActionListener() {
            // ボタンを押したときタイマー機能を起動する
            public void actionPerformed(ActionEvent anEvent){
                threeMinTimerButton.setEnabled(false);
                fiveMinTimerButton.setEnabled(false);

                TimerTask calcRemainingTime = new TimerTask() {
                    public void run(){
                        LocalDateTime nowTime = LocalDateTime.now();
                        Duration remainingTime = Duration.between(nowTime, TimerAction.this.timerTime.plusSeconds(1));
                        dateLabel1.setText(String.format("%02d:%02d", remainingTime.toMinutes(), remainingTime.toSeconds() % 60));
                        if (nowTime.isAfter(TimerAction.this.timerTime.minusSeconds(1))) {
                            timer.cancel();
                            timer = null;
                            threeMinTimerButton.setEnabled(true);
                            fiveMinTimerButton.setEnabled(true);
                        }
                    }
                };

                timerTime = LocalDateTime.now().plusMinutes(3);
                timer = new Timer();
                timer.scheduleAtFixedRate(calcRemainingTime, 0, 100);
            }
        });

        // ボタンが押されたときのアクション
        fiveMinTimerButton.addActionListener(new ActionListener() {
            // ボタンを押したときクロックウィンドウを起動する
            public void actionPerformed(ActionEvent anEvent){
                fiveMinTimerButton.setEnabled(false);
                threeMinTimerButton.setEnabled(false);

                TimerTask calcRemainingTime = new TimerTask() {
                    public void run(){
                        LocalDateTime nowTime = LocalDateTime.now();
                        Duration remainingTime = Duration.between(nowTime, TimerAction.this.timerTime.plusSeconds(1));
                        dateLabel1.setText(String.format("%02d:%02d", remainingTime.toMinutes(), remainingTime.toSeconds() % 60));
                        if (nowTime.isAfter(TimerAction.this.timerTime.minusSeconds(1))) {
                            timer.cancel();
                            timer = null;
                            threeMinTimerButton.setEnabled(true);
                            fiveMinTimerButton.setEnabled(true);
                        }
                    }
                };

                timerTime = LocalDateTime.now().plusMinutes(5);
                timer = new Timer();
                timer.scheduleAtFixedRate(calcRemainingTime, 0, 100);
            }
        });

        mainFrame.add(threeMinTimerButton);
        mainFrame.add(fiveMinTimerButton);

        // ウィンドウ（フレーム）を表示する
        mainFrame.setVisible(true);
    }
}
