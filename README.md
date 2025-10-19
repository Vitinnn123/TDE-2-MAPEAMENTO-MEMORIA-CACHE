# TDE - 2 MAPEAMENTO MEMORIA CACHE
- **Disciplina:** Performance em Sistemas Ciberfísicos
- **Instituição:** Pontifícia Universidade Católica do Paraná    
- **Professor:** Andrey Cabral Meira
- **Alunos:**
  - Bruno Danguy Bortolini — [@snowpuf](https://github.com/snowpuf)   
  - Victor Augusto Esmaniotto — [@Vitinnn123](https://github.com/Vitinnn123)  
---

## Objetivo
O objetivo deste projeto é compreender e comparar o funcionamento das políticas de substituição de páginas FIFO, LRU e MRU, por meio de uma implementação em Java que simula passo a passo as trocas de páginas em diferentes sequências de acesso à memória.

---

## Estrutura do Projeto

```
/Projeto Mapeamento Memória Cache
└── README.md (Documentação do projeto)
  /Código completo (Comentado)
  └── Main.java
  /Código completo (Não Comentado)
  └── Main.java
```

---

## 1. Teste com a sequencia de paginas para 8 quadros:

## a) Sequência A  
**4, 3, 25, 8, 19, 6, 25, 8, 16, 35, 45, 22, 8, 3, 16, 25, 7**

| Algoritmo | Memória final | Quadro que contém a página 7 |
|------------|----------------|-------------------------------|
| **FIFO** | [45, 22, 3, 25, 7, 6, 16, 35] | **5º quadro** |
| **LRU**  | [45, 22, 25, 8, 3, 7, 16, 35] | **6º quadro** |
| **MRU**  | [4, 3, 7, 8, 19, 6, 16, 22] | **3º quadro** |

---

## b) Sequência B  
**4, 5, 7, 9, 46, 45, 14, 4, 64, 7, 65, 2, 1, 6, 8, 45, 14, 11**

| Algoritmo | Memória final | Quadro que contém a página 11 |
|------------|----------------|-------------------------------|
| **FIFO** | [65, 2, 1, 6, 8, 11, 14, 64] | **6º quadro** |
| **LRU**  | [45, 65, 11, 2, 1, 6, 8, 14] | **3º quadro** |
| **MRU**  | [4, 5, 8, 9, 46, 45, 11, 64] | **7º quadro** |

---

## c) Sequência C  
**4, 6, 7, 8, 1, 6, 10, 15, 16, 4, 2, 1, 4, 6, 12, 15, 16, 11**

| Algoritmo | Memória final | Quadro que contém a página 11 |
|------------|----------------|-------------------------------|
| **FIFO** | [2, 4, 6, 12, 11, 10, 15, 16] | **5º quadro** |
| **LRU**  | [4, 6, 2, 12, 1, 11, 15, 16] | **6º quadro** |
| **MRU**  | [2, 12, 7, 8, 4, 10, 15, 11] | **8º quadro** |

---

## 2. Qual a melhor política de substituição?

| Sequência | FIFO | LRU | MRU | Melhor |
|------------|------|------|------|--------|
| A | 13 | 12 | **11** | **MRU** |
| B | **14** | 16 | **14** | **FIFO e MRU (empatou!)** |
| C | 13 | **11** | 12 | **LRU** |

---

## Conclusão

Com esse projeto deu pra entender bem como cada algoritmo de substituição trabalha e o quanto isso muda o desempenho da memória. O FIFO é o mais simples, mas acaba trocando páginas sem pensar se ainda seriam usadas, o que gera mais faltas. O LRU foi o mais equilibrado, porque ele sempre tira a página que ficou mais tempo sem ser usada, o que faz mais sentido na prática. Já o MRU teve bons resultados em alguns testes, mas também pode errar dependendo da sequência. No fim, deu pra ver que escolher a política certa faz toda diferença no número de faltas e na eficiência da memória.

---

## Link Video!



---

## Licença

Este projeto foi desenvolvido **exclusivamente para fins educacionais** na disciplina de *Performance em Sistemas Ciberfísicos* da Pontifícia Universidade Católica do Paraná.
Não possui finalidade comercial e não concede direitos de uso além do contexto acadêmico.
