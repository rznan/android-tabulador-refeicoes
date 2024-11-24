## **PROBLEMÁTICA**  
Muitas pessoas têm dificuldades em seguir uma dieta, seja para ganhar ou perder peso. A falta de acompanhamento e de uma visão clara do que consomem diariamente torna mais desafiador monitorar o progresso e manter o comprometimento com seus objetivos alimentares.

## **SOLUÇÃO PROPOSTA**  
Desenvolver um aplicativo que permita ao usuário registrar suas refeições e os alimentos que as compõem. Cada refeição será composta por diversos itens alimentares, possibilitando um controle mais detalhado e preciso.

O aplicativo apresentará uma visão resumida do consumo recente e histórico, promovendo maior conscientização e motivação para manter uma alimentação controlada. Mesmo com uma abordagem inicial simples, essa funcionalidade básica é suficiente para aumentar a percepção do usuário sobre seus hábitos alimentares e incentivar a adesão a uma dieta mais equilibrada.

## **MVP (Minimum Viable Product)**  
**Estrutura de Dados**

* Classe Abstrata: Consumível
    * Atributos: nome, calorias.
* Classes Derivadas:
    * Alimento:
        * Atributos: proteína, gordura, carboidrato.
    * Bebida:
        * Atributos: volume, açúcar.
    * Classe: Refeição
        * Atributos: data, List\<Consumo\>.

Relacionamento: N para M entre refeições e itens consumíveis.

## **Interface do Usuário**  
Fragments:

* Bebida: Tela para registrar as informações de uma bebida.
* Alimento: Tela para registrar as informações de um alimento.
* Refeição (Resumo): Tela para visualizar o resumo de uma refeição.
* Editar Refeição: Tela para adicionar ou alterar os itens consumidos em uma refeição.

## **Persistência de Dados**  
Banco de dados para armazenar informações de refeições, alimentos e bebidas e as relações entre eles.

## Documento descrevendo o problema
https://drive.google.com/file/d/1z1CmOJwypce6r7TNvS8LjsTNvHVh7shU