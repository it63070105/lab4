package com.example.lab4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Route(value = "index1")
public class MathView extends VerticalLayout {
    private TextField n1, n2, n3;
    private Button btnPlus, btnMinus, btnMultiply, btnDivide, btnMod, btnMax, btn;
    private HorizontalLayout horizontalLayout;
    private Label lb;
    public MathView(){
        horizontalLayout = new HorizontalLayout();
        n1 = new TextField("Number 1");
        n2 = new TextField("Number 2");
        n3 = new TextField("Answer");
        lb = new Label("Operator");
        btnPlus = new Button("+");
        btnMinus = new Button("-");
        btnMultiply = new Button("x");
        btnDivide = new Button("/");
        btnMod = new Button("Mod");
        btnMax = new Button("Max");
        horizontalLayout.add(btnPlus, btnMinus, btnMultiply, btnDivide, btnMod, btnMax);
        add(n1, n2, lb, horizontalLayout, n3);

        btnPlus.addClickListener(event -> {
           double num1 = Double.parseDouble(n1.getValue());
           double num2 = Double.parseDouble(n2.getValue());

           double out = WebClient.create()
                   .get()
                   .uri("http://127.0.0.1:8080/plus/" + num1 + "/" + num2)
                   .retrieve()
                   .bodyToMono(double.class)
                   .block();
           n3.setValue(out+"");
        });

        btnMinus.addClickListener(event -> {
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());

            double out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/minus/" + num1 + "/" + num2)
                    .retrieve()
                    .bodyToMono(double.class)
                    .block();
            n3.setValue(out+"");
        });

        btnMultiply.addClickListener(event -> {
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());

            double out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/multi/" + num1 + "/" + num2)
                    .retrieve()
                    .bodyToMono(double.class)
                    .block();
            n3.setValue(out+"");
        });

        btnDivide.addClickListener(event -> {
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());

            double out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/divide/" + num1 + "/" + num2)
                    .retrieve()
                    .bodyToMono(double.class)
                    .block();
            n3.setValue(out+"");
        });

        btnMod.addClickListener(event -> {
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());

            double out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/mod/" + num1 + "/" + num2)
                    .retrieve()
                    .bodyToMono(double.class)
                    .block();
            n3.setValue(out+"");
        });

        btnMax.addClickListener(event -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//            double num1 = Double.parseDouble(n1.getValue());
//            double num2 = Double.parseDouble(n2.getValue());
            formData.add("n1", n1.getValue());
            formData.add("n2", n2.getValue());

            double out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8080/max")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(double.class)
                    .block();
            n3.setValue(out+"");
        });
    }
}
