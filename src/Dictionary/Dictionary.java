package Dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.regex.Pattern;


public class Dictionary extends JFrame {
    private LinkedList<Word> en;
    private PriorityQueue<Integer> maxheap;

    JTextField TextWord;
    JTextField TextFrequency;
    JTextField TextFreqWord1;
    JTextField TextFreqWord2;
    JTextField TextFreqWord3;
    JTextField TextFilePath;
    JTextArea TextArea;
    JButton ADDButton;
    JButton FINDButton;
    JButton CLEARButton;
    JButton IMPORTButton;
    JButton EXPORTButton;
    JButton REMOVEButton;

    /**
     * Constructor
     * Using GridBagLayout layout manager
     */

    public Dictionary() {
        JFrame frame = new JFrame("Dictionary");
        GridBagLayout gbaglayout = new GridBagLayout();    //创建GridBagLayout
        // 布局管理器
        GridBagConstraints constraints = new GridBagConstraints();
        frame.setLayout(gbaglayout);    //使用GridBagLayout布局管理器
        constraints.fill = GridBagConstraints.BOTH;    //组件填充显示区域
        constraints.weightx = 0.5;    // 指定组件的分配区域
        constraints.weighty = 0.2;
        constraints.gridwidth = 1;
        en = new LinkedList<Word>();
        maxheap = new PriorityQueue<>((a, b) -> b - a);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        makeLabel("Enter Your Word and Frequency Here", frame, gbaglayout,
                constraints);
        constraints.gridwidth = 1;

        FINDButton = makeButton("FIND", frame, gbaglayout, constraints);
        //调用方法，添加按钮组件
        constraints.gridwidth = GridBagConstraints.REMAINDER;  //结束行
        FINDButton.addActionListener(new FindListener());
        ADDButton = makeButton("ADD", frame, gbaglayout, constraints);
        constraints.gridwidth = 1;    //重新设置gridwidth的值
        ADDButton.addActionListener(new AddListener());

        makeLabel("Word", frame, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        TextWord = new JTextField();
        gbaglayout.setConstraints(TextWord, constraints);
        frame.add(TextWord);
        constraints.gridwidth = 1;

        makeLabel("Frequency", frame, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        TextFrequency = new JTextField();
        gbaglayout.setConstraints(TextFrequency, constraints);
        frame.add(TextFrequency);
        constraints.gridwidth = 1;

        makeLabel("Frequent word1", frame, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        TextFreqWord1 = new JTextField();
        gbaglayout.setConstraints(TextFreqWord1, constraints);
        frame.add(TextFreqWord1);
        constraints.gridwidth = 1;

        makeLabel("Frequent word2", frame, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        TextFreqWord2 = new JTextField();
        gbaglayout.setConstraints(TextFreqWord2, constraints);
        frame.add(TextFreqWord2);
        constraints.gridwidth = 1;

        makeLabel("Frequent word3", frame, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        TextFreqWord3 = new JTextField();
        gbaglayout.setConstraints(TextFreqWord3, constraints);
        frame.add(TextFreqWord3);
        constraints.gridwidth = 1;

        IMPORTButton = makeButton("IMPORT", frame, gbaglayout,
                constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        IMPORTButton.addActionListener(new ImportListener());
        EXPORTButton = makeButton("EXPORT", frame, gbaglayout,
                constraints);
        constraints.gridwidth = 1;
        EXPORTButton.addActionListener(new ExportListener());

        makeLabel("File path", frame, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        TextFilePath = new JTextField();
        gbaglayout.setConstraints(TextFilePath, constraints);
        frame.add(TextFilePath);

        TextArea = new JTextArea(10, 5);
        TextArea.setLineWrap(true);
        gbaglayout.setConstraints(TextArea, constraints);
        frame.add(TextArea);
        constraints.gridwidth = GridBagConstraints.REMAINDER;


        CLEARButton = makeButton("CLEAR", frame, gbaglayout,
                constraints);
        CLEARButton.addActionListener(new ClearListener());
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        REMOVEButton = makeButton("REMOVE", frame, gbaglayout,
                constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        REMOVEButton.addActionListener(new RemoveListener());

        frame.setBounds(1100, 400, 800, 700);    //设置容器大小
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static JButton makeButton(String title, JFrame frame,
                                     GridBagLayout gridBagLayout,
                                     GridBagConstraints constraints) {
        JButton button = new JButton(title);    //创建Button对象
        gridBagLayout.setConstraints(button, constraints);
        frame.add(button);
        return button;
    }

    public static JLabel makeLabel(String title, JFrame frame,
                                   GridBagLayout gridBagLayout,
                                   GridBagConstraints constraints) {
        JLabel label = new JLabel(title, JLabel.CENTER);//创建Label对象
        gridBagLayout.setConstraints(label, constraints);
        frame.add(label);
        return label;
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) throws RuntimeException {
            String word = TextWord.getText();
            String meaning = TextArea.getText();
            String frequency = TextFrequency.getText();
            if (Integer.parseInt(frequency) < 0) {
                TextArea.setText("Invalid Frequency Error!");
                throw new InvalidFrequencyError("Invalid Frequency Error!");
            }
            /*if (en.isEmpty()) {*/
            String pattern = "^[A-Za-z]+$";
            if (!Pattern.matches(pattern, word)) {
                TextArea.setText("Invalid Word Error!");
                throw new InvalidWordError("Invalid Word Error!");
            }
            int size = en.size();
            if (size >= 1) {
                for (int i = 0; i < size; i++) {
                    if (!(en.get(i).getName().equals(word))) {
                        en.add(new Word(word, meaning,
                                Integer.valueOf(frequency)));
                        break;
                    } else {
                        TextArea.setText("Word duplicated Error!");
                        throw new WordDuplicatedError("Word duplicated Error!");
                    }
                }
            }
            if (size == 0) {
                Word word2 = new Word(word, meaning,
                        Integer.valueOf(frequency));
                en.add(word2);
            }


            /*for (Word word1 : en) {
                System.out.println(word1.getName());
                System.out.println(word1.getFreq());
                System.out.println(word1.getMeaning());
            }*/
        }
    }

    class FindListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String word = TextWord.getText();

            for (Word word1 : en) {
                if (word1.getName().startsWith(word)) {
                    Integer integer = word1.getFreq();
                    maxheap.add(integer);
                }
            }
            for (int i = 0; i <= 2; i++) {
                Integer poll = maxheap.poll();
                for (Word word2 : en) {
                    if (poll == word2.getFreq() && i == 0) {
                        TextFreqWord1.setText(word2.getName());
                        TextArea.append(word2.getName() + ":" + word2.getMeaning() +
                                "\r\n");
                    } else if (poll == word2.getFreq() && i == 1) {
                        TextFreqWord2.setText(word2.getName());
                        TextArea.append(word2.getName() + ":" + word2.getMeaning() + "\r\n");
                    } else if (poll == word2.getFreq() && i == 2) {
                        TextFreqWord3.setText(word2.getName());
                        TextArea.append(word2.getName() + ":" + word2.getMeaning() + "\r\n");
                    }
                }
            }

        }
    }

    class ImportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) throws RuntimeException {
            String path = TextFilePath.getText();
            if (path.isEmpty() || path == null) {
                TextArea.setText("File not found error!");
                throw new FileNotFoundError("File not found error!");
            }
            try {
                /*ObjectInputStream ois = new ObjectInputStream(new
                FileInputStream(path));
                LinkedList<Word> word = (LinkedList<Word>) ois.readObject();*/
                BufferedReader in =
                        new BufferedReader(new FileReader(path));
                //从文件中读出数据
                String line = null;
                String name = null, meaning = null, freq = null;
                int count = 1;
                while ((line = in.readLine()) != null) {
                    /*System.out.println(line);*/
                    if (count % 4 == 1) {
                        name = line;
                    } else if (count % 4 == 2) {
                        freq = line;
                    } else if (count % 4 == 3) {
                        meaning = line;
                        en.add(new Word(name, meaning, Integer.valueOf(freq)));
                    }
                    count++;
                    /*System.out.println(line);*/
                }
                //3,关闭流
                in.close();
                /*for (Word word1 : en) {
                    System.out.println(word1.getName());
                    System.out.println(word1.getFreq());
                    System.out.println(word1.getMeaning());
                }*/
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) throws RuntimeException {
            String path = TextFilePath.getText();
            if (path.isEmpty() || path == null) {
                TextArea.setText("File not found error!");
                throw new FileNotFoundError("File not found error!");
            }
            try {
                /*ObjectOutputStream oos =
                        new ObjectOutputStream(new FileOutputStream(path));*/
                /*OutputStreamWriter osw = new OutputStreamWriter(oos,
                "UTF-8");*/
                BufferedWriter out =
                        new BufferedWriter(new FileWriter(path));
                Collections.sort(en, (a, b) -> (b.getFreq() - a.getFreq()));
                for (Word word1 : en) {
                    out.write(word1.getName() + "\r\n");//从容器中取数据并输出到文件中
                    out.write(word1.getFreq() + "\r\n");
                    out.write(word1.getMeaning() + "\r\n");
                    out.write("\r\n");
                }
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) throws RuntimeException {
            String word = TextWord.getText();
            if (en.isEmpty()) {
                TextArea.setText("Word not found!");
                throw new WordNotFoundError("Word not found!");
            } else {
                for (Word word1 : en) {
                    if (word1.getName().equals(word)) {
                        en.remove(word1);
                    } else {
                        TextArea.setText("Word not found!");
                        throw new WordNotFoundError("Word not found!");
                    }
                }
            }

        }
    }

    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TextWord.setText("");
            TextFrequency.setText("");
            TextFreqWord1.setText("");
            TextFreqWord2.setText("");
            TextFreqWord3.setText("");
            TextArea.setText("");
        }
    }

    /**
     * main method here
     */
    public static void main(String[] args) {
        Dictionary myDictionary = new Dictionary();

    }
}
