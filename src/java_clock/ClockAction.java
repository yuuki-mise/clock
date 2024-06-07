package java_clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Java演習時計表示プログラム
 * 
 * @author Mise Yuki
 * @version 1.0.0
 */

public class ClockAction extends Object {

    /**
     * performメソッド
     * 時計を表示する
     * 
     * @param なし
     * @return なし
     */
    public void perform() {

        // ウィンドウ（フレーム）を生成する
        JFrame mainFrame = new JFrame("Clock");
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
        dateLabel1.setFont(new Font("MS ゴシック", Font.PLAIN, 60));
        mainFrame.add(dateLabel1);

        // ラベルを追加する
        JLabel dateLabel2 = new JLabel();
        dateLabel2.setText("");
        dateLabel2.setForeground(Color.BLACK);
        // フォントのスタイルをPLAINに設定
        dateLabel2.setFont(new Font("MS ゴシック", Font.PLAIN, 60));
        mainFrame.add(dateLabel2);

        // ボタンを追加する
        JButton StopWatchButton = new JButton("ストップウォッチ");
        StopWatchButton.addActionListener(new ActionListener() {
            // ボタンを押したときストップウォッチウィンドウを起動する
            public void actionPerformed(ActionEvent anEvent) {
                StopWatchAction StopWatch = new StopWatchAction();
                StopWatch.perform();
                // クロックウィンドウの表示をやめる
                mainFrame.setVisible(false);
            }
        });
        mainFrame.add(StopWatchButton);

        // ボタンを追加する
        JButton TimerButton = new JButton("タイマー");
        TimerButton.addActionListener(new ActionListener() {
            // ボタンを押したときタイマーウィンドウを起動する
            public void actionPerformed(ActionEvent anEvent) {
                TimerAction Timer = new TimerAction();
                Timer.perform();
                // クロックウィンドウの表示をやめる
                mainFrame.setVisible(false);
            }
        });
        mainFrame.add(TimerButton);

        // ウィンドウ（フレーム）を表示する
        mainFrame.setVisible(true);

        // 時刻更新処理の定義
        TimerTask aTask = new TimerTask() {
            public void run() {
                dateLabel1
                        .setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒 E曜日")));
            }
        };

        // 時刻更新を1秒ごとに実行
        Timer updateDateTime = new Timer();
        updateDateTime.scheduleAtFixedRate(aTask, 0, 1000);

    }

}
