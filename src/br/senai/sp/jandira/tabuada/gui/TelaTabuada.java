package br.senai.sp.jandira.tabuada.gui;

import br.senai.sp.jandira.tabuada.model.Tabuada;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class TelaTabuada extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Definir o tamanho da tela
        //stage.setWidth(500);
        //stage.setHeight(500);
        stage.setTitle("Tabuada");
        stage.setResizable(false);


        //Criar o root - componente de layout principal
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #f6df58");


        //Criamos a cena e colocamos o root nela
        Scene scene = new Scene(root);


        //Criar o header da tela
        VBox header = new VBox();
        header.setPrefHeight(100);
        header.setStyle("-fx-background-color: #ffbf1e");

        //Colocar conteúdo do header
        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setPadding(new Insets(16, 8, 0, 16));
        labelTitulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 30");

        Label labelSubTitulo = new Label("Crie a tabuada que sua imaginação mandar.");
        labelSubTitulo.setPadding(new Insets(0, 8, 8, 8));
        labelSubTitulo.setStyle("-fx-text-fill: white");

        //Colocar os labels dentro do header
        header.getChildren().addAll(labelTitulo, labelSubTitulo);


        //Criar o grid formulário
        GridPane gridFormulario = new GridPane();
        gridFormulario.setVgap(10);
        gridFormulario.setHgap(10);
        gridFormulario.setPadding(new Insets(16, 8, 16, 8));
        //gridFormulario.setPrefHeight(100);
        //gridFormulario.setStyle("-fx-background-color: yellow");

        //Criar o conteúdo do grid
        Label labelMultiplicando = new Label("Multiplicador: ");
        labelMultiplicando.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13");
        TextField tfMultiplicando = new TextField();
        Label labelMenorMultiplicador = new Label("Menor multiplicador: ");
        labelMenorMultiplicador.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13");
        TextField tfMenorMultiplicador = new TextField();
        Label labelMaiorMultiplicador = new Label("Maior multiplicador: ");
        labelMaiorMultiplicador.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13");
        TextField tfMaiorMultiplicador = new TextField();


        //Colocar os componentes no formulário
        gridFormulario.add(labelMultiplicando, 0, 0);
        gridFormulario.add(tfMultiplicando, 1, 0);
        gridFormulario.add(labelMenorMultiplicador, 0, 1);
        gridFormulario.add(tfMenorMultiplicador, 1, 1);
        gridFormulario.add(labelMaiorMultiplicador, 0, 2);
        gridFormulario.add(tfMaiorMultiplicador, 1, 2);


        //Criar a caixa dos botões
        Pane paneButtons = new Pane();
        paneButtons.setPadding(new Insets(16, 8, 16, 8));

        HBox boxBotoes = new HBox();
        boxBotoes.setSpacing(12);
        paneButtons.getChildren().add(boxBotoes);


        //Criar os botões
        Button btnCalcular = new Button("Calcular");
        Button btnLimpar = new Button("Limpar");
        Button btnSair = new Button("Sair");

        //Colocar os botões na caixa
        boxBotoes.getChildren().addAll(btnCalcular, btnLimpar, btnSair);


        //Criar a caixa de resultado
        VBox boxResultado = new VBox();
        boxResultado.setPrefHeight(300);
        //boxResultado.setStyle("-fx-background-color: red");


        //Criar componentes da boxResultados
        Label labelResultado = new Label("Resultado: ");
        labelResultado.setPadding(new Insets(8, 8, 8, 8));
        labelResultado.setStyle("-fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 15");

        ListView listaTabuada = new ListView();
        listaTabuada.setPadding(new Insets(8));

        //Adicionar componentes na boxResultados
        boxResultado.getChildren().addAll(labelResultado, listaTabuada);



        //Adicionar componentes ao root
        root.getChildren().add(header);
        root.getChildren().add(gridFormulario);
        root.getChildren().add(paneButtons);
        root.getChildren().add(boxResultado);



        //Colocamos a cena no palco
        stage.setScene(scene);

        stage.show();

        btnLimpar.setOnAction((ActionEvent event) -> {
            tfMultiplicando.clear();
            tfMenorMultiplicador.clear();
            tfMaiorMultiplicador.clear();
            listaTabuada.getItems().clear();
            tfMultiplicando.requestFocus();
        });

        btnCalcular.setOnAction(event -> {
            Tabuada tabuada = new Tabuada();
            tabuada.multiplicando =
                    Integer.parseInt(tfMultiplicando.getText());

            tabuada.multiplicadorInicial =
                    Integer.parseInt(tfMenorMultiplicador.getText());

            tabuada.multiplicadorFinal =
                    Integer.parseInt(tfMaiorMultiplicador.getText());

            String[] resultado = tabuada.calcularTabuada();
            listaTabuada.getItems().addAll(resultado);

            //Gravar os dados da tabuada em arquivo
            Path arquivo = Path.of("c:\\Users\\25203836\\DS1T\\Tabuada\\dados_tabuadas.csv");

            String dados = tfMultiplicando.getText() + ";" + tfMenorMultiplicador.getText() + ";" + tfMenorMultiplicador.getText() + ";" + tfMaiorMultiplicador.getText() + ";" ;String s = LocalDateTime.now() + "\n";

            try{
                Files.writeString(arquivo, dados, StandardOpenOption.APPEND);
            } catch (IOException erro){
                System.out.println(erro.getMessage());
            }


        });
    }
}
