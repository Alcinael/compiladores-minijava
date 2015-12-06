A equipe é formada por:
Jefferson Gurguri, Lucas Gonçalves, Dionísio do Reino e Francisco Airton

Projeto da cadeira de Construção de Compiladores I (2012.1) da Universidade Federal do Ceará, sob a orientação do professor Heron Carvalho. O projeto tem como objetivo implementar um compilador na linguagem Java, seguindo o livro "Modern Compiler Implementation in Java" de Andrew W. Appel, Jens Palsberg e utilizando o framework fornecido pelos autores do livro. O trabalho foi igualmente dividido entre os membros da equipe ficando todos responsáveis por entender e ajudar na implementação das partes.

FRONT-END

Análise Léxica

A análise léxica consiste em identificar quais tokens compõem dada string de entrada.

Status

Implementação: Concluída

Testes: Concluídos com sucesso

Parser

O parser sintático consiste em verificar se o código de entrada está escrito com a sintaxe correta da linguagem fonte e, caso esteja, gerar uma árvore descrevendo uma derivação que resulta neste código.

Status

Implementação: Concluída

Testes: Concluídos com sucesso

Árvore Sintática Abstrata

Geramos uma árvore que melhor representa o código, assim separamos a análise sintática da análise semântica e utilizamos esta árvore para ajudar nos próximos passos, como otimizações. A árvore é criada durante o percurso da análise sintática.

Status

Implementação: Concluída

Testes: Concluídos com sucesso

Análise Semântica

Conectamos as definições de variáveis ao uso delas, checamos se cada expressão tem o tipo correto e traduzimos a sintaxe abstrata em uma representação mais simples para a geração do código de máquina.

Status

Implementação: Parcialmente concluída

Testes: Parcialmente concluídos com sucesso

Registros de Ativação

Frames são utilizados nas linguagens de programação para indicar onde estão localizadas as variáveis locais e assinaturas de métodos. Utilizamos a classe MipsFrame.java que nos foi enviada para implementar o Frame.

Status

Implementação: Concluída

Testes: --

Tradução para Representação Intermediária

A tradução para código intermediário é a passagem do Front-End para o Back-End do compilador. Foi o ponto da implementação que encontramos maiores dificuldades.

Status

Implemtentação: Parcialmente concluída

Testes:  Parcialmente concluídos com sucesso

Árvore Canônica:

A árvore de linguagem intermediária ainda está um pouco ligada à linguagem fonte. Para simplificar a transformação da árvore IR para o código de máquina fazemos o que chamamos de canonização, que consiste em modificá-la a fim de torná-la mais próxima da linguagem de máquina, como, por exemplo, reescrever partes da IR para que a ordem de avaliação de parâmetros não interfira no resultado.

Status

Implementação: Parcialmente concluída

Testes: Parcialmente concluídos com sucesso

Blocos Básicos e Traços

O programa é seperado em blocos de comando e, em seguida, calculado os traços a partir do algoritmo de geração de traços de tal forma que cada bloco esteja em um traço e dois traços não possuem blocos em comum. Assim, podemos ordenar os blocos de tal forma que o bloco referente a uma condição Falsa de um CJUMP esteja imediatamente após seu bloco para eliminarmos os CJUMP's, pois no código de máquina só possuimos JUMP's. Os blocos são criados a partir da árvore canônica.

Status

Implementação: Parcialmente concluída

Testes: Parcialmente concluídos com sucesso

Seleção de Instruções

A partir da árvore IR canônica podemos realizar a tradução para a linguagem de máquina de modo simples. Usamos a seleção de instrução para substituir cada ladrilho da árvore canônica por sua instrução de máquina correspondente utilizando o algoritmo MaximalMunch. Além disso, são determinados os temporários de valores intermediários calculados por expressões.

Status

Implementação: A equipe não implementou a seleção de instruções.

Testes: --

Alocação de Registradores

Nesta fase, construimos o grafo de interferência onde os nós são os temporários utilizados nas instruções e os registradores. Se existe aresta entre dois nós, representa que os nós estão vivos ao mesmo tempo na execução do programa. Obtemos estas informações a partir da análise de longevidade.

Status

Implementação: A equipe não implementou a seleção de instruções.

Testes: --

O teste completo do compilador não pode ser executado devido à falta de partes do mesmo.