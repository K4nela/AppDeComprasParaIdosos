import { ArrowLeft, Plus, Edit, Trash2, Package } from 'lucide-react';

interface ManageItemsScreenProps {
  onNavigate: (screen: string) => void;
}

export function ManageItemsScreen({ onNavigate }: ManageItemsScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('visualizar-lista')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <div>
            <h1 className="text-gray-800">Gerenciar Itens</h1>
            <p className="text-gray-500 text-sm">Controle total sobre seus itens de desejo</p>
          </div>
        </div>

        {/* Info for Elder */}
        <div className="bg-blue-100 border-2 border-blue-200 rounded-2xl p-4 mb-6">
          <div className="flex gap-3">
            <div className="text-blue-600">✏️</div>
            <div>
              <div className="text-blue-800 mb-1">Gerenciamento Completo</div>
              <p className="text-blue-700 text-sm">Como idoso, você tem permissão para adicionar, editar e excluir itens da sua lista.</p>
            </div>
          </div>
        </div>

        {/* Action Cards */}
        <div className="grid md:grid-cols-3 gap-6 mb-8">
          {/* Cadastrar Item */}
          <button 
            onClick={() => onNavigate('editar-item')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-green-100 to-green-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-green-200 group-hover:to-green-300 transition-all">
              <Plus className="w-10 h-10 text-green-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Cadastrar Item</h2>
            <p className="text-gray-500">Adicione um novo item à sua lista de desejos</p>
          </button>

          {/* Editar Item */}
          <button 
            onClick={() => onNavigate('editar-item')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-blue-100 to-blue-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-blue-200 group-hover:to-blue-300 transition-all">
              <Edit className="w-10 h-10 text-blue-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Editar Item</h2>
            <p className="text-gray-500">Modifique as informações de um item existente</p>
          </button>

          {/* Excluir Item */}
          <button className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105">
            <div className="bg-gradient-to-br from-red-100 to-red-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-red-200 group-hover:to-red-300 transition-all">
              <Trash2 className="w-10 h-10 text-red-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Excluir Item</h2>
            <p className="text-gray-500">Remova um item da sua lista permanentemente</p>
          </button>
        </div>

        {/* Current Items Preview */}
        <div className="bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-800 mb-4">Itens Atuais da Lista</h3>
          <div className="space-y-3">
            <div className="flex items-center gap-4 p-4 bg-gray-50 rounded-xl hover:bg-gray-100 transition-colors cursor-pointer">
              <div className="bg-purple-200 p-3 rounded-lg">
                <Package className="w-6 h-6 text-purple-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-800">Cadeira de Balanço</div>
                <div className="text-gray-500 text-sm">Loja de Móveis Casa & Lar • Qtd: 1</div>
              </div>
              <div className="flex gap-2">
                <button 
                  onClick={() => onNavigate('editar-item')}
                  className="p-2 bg-blue-100 text-blue-600 rounded-lg hover:bg-blue-200 transition-colors"
                >
                  <Edit className="w-4 h-4" />
                </button>
                <button className="p-2 bg-red-100 text-red-600 rounded-lg hover:bg-red-200 transition-colors">
                  <Trash2 className="w-4 h-4" />
                </button>
              </div>
            </div>

            <div className="flex items-center gap-4 p-4 bg-gray-50 rounded-xl hover:bg-gray-100 transition-colors cursor-pointer">
              <div className="bg-blue-200 p-3 rounded-lg">
                <Package className="w-6 h-6 text-blue-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-800">Livro de Receitas</div>
                <div className="text-gray-500 text-sm">Livraria Central • Qtd: 1</div>
              </div>
              <div className="flex gap-2">
                <button 
                  onClick={() => onNavigate('editar-item')}
                  className="p-2 bg-blue-100 text-blue-600 rounded-lg hover:bg-blue-200 transition-colors"
                >
                  <Edit className="w-4 h-4" />
                </button>
                <button className="p-2 bg-red-100 text-red-600 rounded-lg hover:bg-red-200 transition-colors">
                  <Trash2 className="w-4 h-4" />
                </button>
              </div>
            </div>

            <div className="flex items-center gap-4 p-4 bg-gray-50 rounded-xl hover:bg-gray-100 transition-colors cursor-pointer">
              <div className="bg-green-200 p-3 rounded-lg">
                <Package className="w-6 h-6 text-green-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-800">Kit de Jardinagem</div>
                <div className="text-gray-500 text-sm">Jardim & Cia • Qtd: 1</div>
              </div>
              <div className="flex gap-2">
                <button 
                  onClick={() => onNavigate('editar-item')}
                  className="p-2 bg-blue-100 text-blue-600 rounded-lg hover:bg-blue-200 transition-colors"
                >
                  <Edit className="w-4 h-4" />
                </button>
                <button className="p-2 bg-red-100 text-red-600 rounded-lg hover:bg-red-200 transition-colors">
                  <Trash2 className="w-4 h-4" />
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
