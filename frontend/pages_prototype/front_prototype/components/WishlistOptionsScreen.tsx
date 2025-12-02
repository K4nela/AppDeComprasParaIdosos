import { ArrowLeft, PlusCircle, ListChecks, Edit3 } from 'lucide-react';

interface WishlistOptionsScreenProps {
  onNavigate: (screen: string) => void;
}

export function WishlistOptionsScreen({ onNavigate }: WishlistOptionsScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('home-idoso')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <h1 className="text-gray-800">Lista de Desejos</h1>
        </div>

        {/* Action Cards */}
        <div className="grid md:grid-cols-3 gap-6">
          {/* Criar Lista */}
          <button 
            onClick={() => onNavigate('criar-lista')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-green-100 to-green-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-green-200 group-hover:to-green-300 transition-all">
              <PlusCircle className="w-10 h-10 text-green-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Criar Lista</h2>
            <p className="text-gray-500">Crie uma nova lista de desejos para compartilhar com seus familiares</p>
          </button>

          {/* Acessar Lista */}
          <button 
            onClick={() => onNavigate('visualizar-lista')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-purple-100 to-purple-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-purple-200 group-hover:to-purple-300 transition-all">
              <ListChecks className="w-10 h-10 text-purple-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Minhas Listas</h2>
            <p className="text-gray-500">Visualize e gerencie suas listas e seus itens</p>
          </button>

          {/* Editar Lista */}
          <button 
            onClick={() => onNavigate('criar-lista')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-blue-100 to-blue-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-blue-200 group-hover:to-blue-300 transition-all">
              <Edit3 className="w-10 h-10 text-blue-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Editar Lista</h2>
            <p className="text-gray-500">Modifique nome e descrição das suas listas</p>
          </button>
        </div>

        {/* Minhas Listas */}
        <div className="mt-8 bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-800 mb-4">Minhas Listas Criadas</h3>
          <div className="space-y-3">
            <div 
              onClick={() => onNavigate('visualizar-lista')}
              className="flex items-center justify-between p-4 bg-purple-50 rounded-xl hover:bg-purple-100 transition-colors cursor-pointer"
            >
              <div className="flex items-center gap-3">
                <div className="bg-purple-200 p-2 rounded-lg">
                  <ListChecks className="w-5 h-5 text-purple-600" />
                </div>
                <div>
                  <div className="text-gray-800">Aniversário de 80 Anos</div>
                  <div className="text-gray-500 text-sm">Criada em 15/11/2024 • 8 itens</div>
                </div>
              </div>
              <div className="flex items-center gap-2">
                <span className="px-3 py-1 bg-green-100 text-green-600 rounded-full text-sm">3 Concluídos</span>
              </div>
            </div>

            <div 
              onClick={() => onNavigate('visualizar-lista')}
              className="flex items-center justify-between p-4 bg-blue-50 rounded-xl hover:bg-blue-100 transition-colors cursor-pointer"
            >
              <div className="flex items-center gap-3">
                <div className="bg-blue-200 p-2 rounded-lg">
                  <ListChecks className="w-5 h-5 text-blue-600" />
                </div>
                <div>
                  <div className="text-gray-800">Natal 2024</div>
                  <div className="text-gray-500 text-sm">Criada em 01/12/2024 • 5 itens</div>
                </div>
              </div>
              <div className="flex items-center gap-2">
                <span className="px-3 py-1 bg-yellow-100 text-yellow-600 rounded-full text-sm">2 Pendentes</span>
              </div>
            </div>

            <div 
              onClick={() => onNavigate('visualizar-lista')}
              className="flex items-center justify-between p-4 bg-green-50 rounded-xl hover:bg-green-100 transition-colors cursor-pointer"
            >
              <div className="flex items-center gap-3">
                <div className="bg-green-200 p-2 rounded-lg">
                  <ListChecks className="w-5 h-5 text-green-600" />
                </div>
                <div>
                  <div className="text-gray-800">Desejos Gerais</div>
                  <div className="text-gray-500 text-sm">Criada em 05/10/2024 • 12 itens</div>
                </div>
              </div>
              <div className="flex items-center gap-2">
                <span className="px-3 py-1 bg-blue-100 text-blue-600 rounded-full text-sm">1 Em Andamento</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
