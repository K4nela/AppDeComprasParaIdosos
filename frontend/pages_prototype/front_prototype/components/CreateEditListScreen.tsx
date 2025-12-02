import { ArrowLeft, Save, Heart } from 'lucide-react';

interface CreateEditListScreenProps {
  onNavigate: (screen: string) => void;
}

export function CreateEditListScreen({ onNavigate }: CreateEditListScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-2xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('opcoes-lista')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <div className="bg-gradient-to-br from-purple-200 to-blue-200 p-3 rounded-full">
            <Heart className="w-8 h-8 text-purple-600" />
          </div>
          <h1 className="text-gray-800">Criar Nova Lista</h1>
        </div>

        {/* Form Card */}
        <div className="bg-white rounded-3xl shadow-xl p-8">
          <div className="space-y-6">
            {/* Nome da Lista */}
            <div>
              <label className="block text-gray-700 mb-2">Nome da Lista</label>
              <input
                type="text"
                placeholder="Ex: Aniversário de 80 Anos"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-purple-300 focus:outline-none transition-colors"
              />
              <p className="text-gray-500 text-sm mt-2">Escolha um nome que identifique facilmente esta lista</p>
            </div>

            {/* Descrição */}
            <div>
              <label className="block text-gray-700 mb-2">Descrição</label>
              <textarea
                placeholder="Descreva o propósito desta lista de desejos..."
                rows={4}
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-purple-300 focus:outline-none transition-colors resize-none"
              />
              <p className="text-gray-500 text-sm mt-2">Adicione detalhes sobre a ocasião ou finalidade</p>
            </div>

            {/* Preview */}
            <div className="bg-gradient-to-r from-purple-50 to-blue-50 rounded-2xl p-6">
              <h3 className="text-gray-800 mb-3">Pré-visualização</h3>
              <div className="bg-white rounded-xl p-4">
                <div className="flex items-center gap-3 mb-2">
                  <div className="bg-purple-200 p-2 rounded-lg">
                    <Heart className="w-5 h-5 text-purple-600" />
                  </div>
                  <div className="text-gray-800">Aniversário de 80 Anos</div>
                </div>
                <p className="text-gray-500 text-sm">Desejos especiais para comemorar essa data importante...</p>
                <div className="flex gap-2 mt-3">
                  <span className="px-3 py-1 bg-gray-100 text-gray-600 rounded-full text-sm">0 itens</span>
                  <span className="px-3 py-1 bg-purple-100 text-purple-600 rounded-full text-sm">Criada hoje</span>
                </div>
              </div>
            </div>

            {/* Action Buttons */}
            <div className="flex gap-3 pt-4">
              <button 
                onClick={() => onNavigate('opcoes-lista')}
                className="flex-1 bg-white border-2 border-gray-200 text-gray-700 py-4 rounded-xl hover:bg-gray-50 transition-all"
              >
                Cancelar
              </button>
              <button 
                onClick={() => onNavigate('visualizar-lista')}
                className="flex-1 bg-gradient-to-r from-purple-300 to-blue-300 text-gray-800 py-4 rounded-xl hover:from-purple-400 hover:to-blue-400 transition-all shadow-md hover:shadow-lg flex items-center justify-center gap-2"
              >
                <Save className="w-5 h-5" />
                Salvar Lista
              </button>
            </div>
          </div>
        </div>

        {/* Help Tip */}
        <div className="mt-6 bg-blue-100 border-2 border-blue-200 rounded-2xl p-4">
          <div className="flex gap-3">
            <div className="text-blue-600">💡</div>
            <div>
              <div className="text-blue-800 mb-1">Dica</div>
              <p className="text-blue-700 text-sm">Após criar a lista, você poderá adicionar itens de desejo. Seus familiares poderão visualizar e atualizar o status dos itens!</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
