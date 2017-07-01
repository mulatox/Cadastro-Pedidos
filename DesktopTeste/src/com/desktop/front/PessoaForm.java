package com.desktop.front;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import com.desktop.model.Cliente;
import com.towel.bean.Formatter;
import com.towel.bind.Binder;
import com.towel.bind.annotation.*;
@Form(Cliente.class)
// Form para Pessoa
public class PessoaForm extends JFrame {
    @Bindable(field = "nome")
    private JTextField nome;
    private Binder binder;
    public PessoaForm() {
        super("PessoaForm");
        nome = new JTextField(20);
        JButton add = new JButton("Add");
        JButton load = new JButton("Load");
        setLayout(new GridLayout(4, 2));
        add(new JLabel("Nome:"));
        add(nome);
        add(add);
        add(load);
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	Cliente pessoa = new Cliente();
                pessoa.setNome("Marky");
                binder.updateView(pessoa);
            }
        });
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Cliente pessoa = new Cliente();
                binder.updateModel(pessoa);
                System.out.println(pessoa.getNome());
            }
        });
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        binder = new AnnotatedBinder(this);
    }
    public static void main(String[] args) {
        new PessoaForm().setVisible(true);
    }
    // IntFormatter sera usado para transformar a String em numero.
    public static class IntFormatter implements Formatter {
        public Object format(Object obj) {
            Integer d = (Integer) obj;
            return d.toString();
        }
        public Object parse(Object obj) {
            return Integer.valueOf(Integer.parseInt((String) obj));
        }
        public String getName() {
            return "int";
        }
    }
}