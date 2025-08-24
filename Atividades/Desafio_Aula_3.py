import threading
import random
import time

'''
    Desafio para depois do intervalo: Fazer um programa na sua linguagem de preferência que: 
    i) distribua n números em m listas de inteiros, sendo que os números devem variar entre 1000 a 100000 
    ii) que faça a média dos valores gerados em todas as listas 
    iii) circundar as rotinas com threads, desde que possível
'''

# Rotina que popula uma lista com números aleatórios
def popular_lista(lista_a_popular, numeros_por_lista):
    for _ in range(numeros_por_lista):
        numero_aleatorio = random.randint(1000, 100000)
        lista_a_popular.append(numero_aleatorio)


n_total_numeros = int(input("Digite o número total de elementos (n): "))
m_total_listas = int(input("Digite o número de listas (m): "))

print(f"\nDistribuindo {n_total_numeros} números em {m_total_listas} listas")

# lista que irá armazenar as m listas
lista_de_listas = [[] for _ in range(m_total_listas)]
# Lista vazia que serve para armazenar os futuros objetos de thread
threads = []

# Calcula quantos números cada lista deve ter
numeros_por_lista_base = n_total_numeros // m_total_listas
numeros_restantes = n_total_numeros % m_total_listas

inicio = time.time()

# Circundando a rotina de povoamento com threads
for i in range(m_total_listas):
    # Distribui o resto da divisão para as primeiras listas
    numeros_nesta_lista = numeros_por_lista_base + (1 if i < numeros_restantes else 0)
    
    if numeros_nesta_lista > 0:
        # Cria a thread passando a função alvo e seus argumentos
        thread = threading.Thread(target=popular_lista, args=(lista_de_listas[i], numeros_nesta_lista))
        threads.append(thread)
        # Inicia a execução da thread
        thread.start()

# Espera todas as threads terminarem
for thread in threads:
    thread.join()

fim = time.time()
print(f"Listas populadas em {fim - inicio:.4f} segundos.")

# Cálculo da média
print("\nCalculando a média de todos os valores")
soma_total = 0
total_de_elementos = 0

# Cálculo da thread principal
for sublista in lista_de_listas:
    soma_total += sum(sublista)
    total_de_elementos += len(sublista)

media = soma_total / total_de_elementos
print(f"Soma de todos os elementos: {soma_total}")
print(f"Número de elementos: {total_de_elementos}")
print(f"Média geral: {media:.2f}")
