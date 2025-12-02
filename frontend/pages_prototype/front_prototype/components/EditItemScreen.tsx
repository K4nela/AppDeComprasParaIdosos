import { ArrowLeft, Save, Package } from 'lucide-react';

interface EditItemScreenProps {
  onNavigate: (screen: string) => void;
}

export function EditItemScreen({ onNavigate }: EditItemScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-2xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('itens-lista')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <div className="bg-gradient-to-br from-purple-200 to-blue-200 p-3 rounded-full">
            <Package className="w-8 h-8 text-purple-600" />
          </div>
          <h1 className="text-gray-800">Editar Item</h1>
        </div>

        {/* Form Card */}
        <div className="bg-white rounded-3xl shadow-xl p-8">
          <div className="space-y-5">
            {/* Nome do Item */}
            <div>
              <label className="block text-gray-700 mb-2">Nome do Item</label>
              <input
                type="text"
                defaultValue="Cadeira de Balanço"
                placeholder="Ex: Livro de receitas"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-purple-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Descrição */}
            <div>
              <label className="block text-gray-700 mb-2">Descrição</label>
              <textarea
                defaultValue="Cadeira confortável para relaxar na varanda"
                placeholder="Descreva o item em detalhes..."
                rows={4}
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-purple-300 focus:outline-none transition-colors resize-none"
              />
            </div>

            {/* Quantidade */}
            <div>
              <label className="block text-gray-700 mb-2">Quantidade</label>
              <input
                type="number"
                defaultValue="1"
                min="1"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-purple-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Nome da Loja */}
            <div>
              <label className="block text-gray-700 mb-2">Nome da Loja</label>
              <input
                type="text"
                defaultValue="Loja de Móveis Casa & Lar"
                placeholder="Onde encontrar este item"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-purple-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Link */}
            <div>
              <label className="block text-gray-700 mb-2">Link do Produto (opcional)</label>
              <input
                type="url"
                defaultValue="https://exemplo.com/cadeira"
                placeholder="https://..."
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-purple-300 focus:outline-none transition-colors"
              />
              <p className="text-gray-500 text-sm mt-2">Cole o link da página do produto para facilitar a compra</p>
            </div>

            {/* Preview */}
            <div className="bg-gradient-to-r from-purple-50 to-blue-50 rounded-2xl p-6">
              <h3 className="text-gray-800 mb-3">Pré-visualização</h3>
              <div className="bg-white rounded-xl p-4">
                <div className="flex items-center gap-3 mb-2">
                  <div className="bg-purple-200 p-2 rounded-lg">
                    <Package className="w-5 h-5 text-purple-600" />
                  </div>
                  <div className="text-gray-800">Cadeira de Balanço</div>
                </div>
                <p className="text-gray-500 text-sm mb-3">Cadeira confortável para relaxar na varanda</p>
                <div className="flex gap-2 text-sm text-gray-600">
                  <span>Qtd: 1</span>
                  <span>•</span>
                  <span>Loja de Móveis Casa & Lar</span>
                </div>
              </div>
            </div>

            {/* Action Buttons */}
            <div className="flex gap-3 pt-4">
              <button 
                onClick={() => onNavigate('itens-lista')}
                className="flex-1 bg-white border-2 border-gray-200 text-gray-700 py-4 rounded-xl hover:bg-gray-50 transition-all"
              >
                Cancelar
              </button>
              <button 
                onClick={() => onNavigate('visualizar-lista')}
                className="flex-1 bg-gradient-to-r from-purple-300 to-blue-300 text-gray-800 py-4 rounded-xl hover:from-purple-400 hover:to-blue-400 transition-all shadow-md hover:shadow-lg flex items-center justify-center gap-2"
              >
                <Save className="w-5 h-5" />
                Salvar Item
              </button>
            </div>
          </div>
        </div>

        {/* Help Tip */}
        <div className="mt-6 bg-purple-100 border-2 border-purple-200 rounded-2xl p-4">
          <div className="flex gap-3">
            <div className="text-purple-600">💡</div>
            <div>
              <div className="text-purple-800 mb-1">Dica</div>
              <p className="text-purple-700 text-sm">Seja específico na descrição para que seus familiares entendam exatamente o que você deseja!</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
