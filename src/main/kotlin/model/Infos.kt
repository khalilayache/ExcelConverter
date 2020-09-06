package model

data class Account(val name: String, val transactions: MutableList<Transaction> = mutableListOf())


data class Transaction(val date: Double,
                       val description: String,
                       val value: Double,
                       val category: String)




/*
Nubank
Itaú
Carteira
Easynvest
Trigg
NuConta
C6
Diin
*/


/*

Financiamento
IOF
Multas
Serviços Financeiros
Consumo
Alimentação
Ingresso
Outros Gastos
Bar
Guloseimas
Táxi
Salão de Beleza
Salário
Plano de Saúde
Plano Odontológico
Sem Categoria
Internet
Celular
Tarifas bancárias
Freelas
Presentes
Transferência
Depósito
Saques
Metrô/Ônibus
Estacionamento
Outras Receitas
Balada
Locomoção
Cinema/Teatro
Estádia
Passeios
Perfumaria
Roupas e Calçados
Outros
Saúde
Almoço
Gastos Esporádicos
Livraria
Transporte
Padaria
Acessórios
Dentista
Impostos
Cursos
Juros de Investimentos
Lazer
13º Salário
Gastos Pessoais
Reembolso
Pedágio
Eletrônicos
Farmácia
Bônus
Anuidade Cartão
Jogos
Férias
Viagens
Mobilidade
Doações
Comissão
Móveis e decoração
IR
Utensílios Domésticos
Combustível
Manutenção da Casa
* */