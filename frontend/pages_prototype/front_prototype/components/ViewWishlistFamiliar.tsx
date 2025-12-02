import { ArrowLeft, Heart, Calendar, Package, ExternalLink, User } from 'lucide-react';

interface ViewWishlistFamiliarProps {
  onNavigate: (screen: string) => void;
}

export function ViewWishlistFamiliar({ onNavigate }: ViewWishlistFamiliarProps) {
  const wishlists = [
    {
      id: 1,
      elderName: 'Maria Santos',
      listName: 'Aniversário de 80 Anos',
      created: '15/11/2024',
      totalItems: 8,
      pending: 5,
      inProgress: 2,
      completed: 1,
    },
    {
      id: 2,
      elderName: 'João Silva',
      listName: 'Natal 2024',
      created: '01/12/2024',
      totalItems: 5,
      pending: 3,
      inProgress: 1,
      completed: 1,
    },
    {
      id: 3,
      elderName: 'Ana Costa',
      listName: 'Desejos Gerais',
      created: '05/10/2024',
      totalItems: 12,
      pending: 4,
      inProgress: 3,
      completed: 5,
    },
  ];

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-5xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('home-familiar')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <h1 className="text-gray-800">Listas dos Idosos</h1>
        </div>

        {/* Info Banner */}
        <div className="bg-purple-100 border-2 border-purple-200 rounded-2xl p-4 mb-6">
          <div className="flex gap-3">
            <div className="text-purple-600">ℹ️</div>
            <div>
              <div className="text-purple-800 mb-1">Modo de Visualização</div>
              <p className="text-purple-700 text-sm">Você pode visualizar as listas e atualizar o status dos itens, mas não pode criar ou editar listas.</p>
            </div>
          </div>
        </div>

        {/* Wishlists Grid */}
        <div className="space-y-6">
          {wishlists.map((wishlist) => (
            <div key={wishlist.id} className="bg-white rounded-3xl shadow-xl p-8">
              {/* Wishlist Header */}
              <div className="flex items-start justify-between mb-6">
                <div className="flex items-center gap-4">
                  <div className="bg-gradient-to-br from-purple-200 to-blue-200 p-4 rounded-2xl">
                    <Heart className="w-10 h-10 text-purple-600" />
                  </div>
                  <div>
                    <div className="flex items-center gap-2 mb-1">
                      <User className="w-4 h-4 text-gray-500" />
                      <span className="text-gray-600">{wishlist.elderName}</span>
                    </div>
                    <h2 className="text-gray-800">{wishlist.listName}</h2>
                    <div className="flex items-center gap-2 text-gray-500 mt-1">
                      <Calendar className="w-4 h-4" />
                      <span className="text-sm">Criada em {wishlist.created}</span>
                      <span className="mx-2">•</span>
                      <span className="text-sm">ID: #LST-{wishlist.id.toString().padStart(3, '0')}</span>
                    </div>
                  </div>
                </div>
                <button 
                  onClick={() => onNavigate('visualizar-lista-detalhes')}
                  className="bg-gradient-to-r from-purple-300 to-blue-300 text-gray-800 px-6 py-3 rounded-xl hover:from-purple-400 hover:to-blue-400 transition-all shadow-md hover:shadow-lg"
                >
                  Ver Itens
                </button>
              </div>

              {/* Stats */}
              <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
                <div className="bg-gray-50 p-4 rounded-xl text-center">
                  <div className="text-gray-800 mb-1">{wishlist.totalItems}</div>
                  <div className="text-gray-500 text-sm">Total de Itens</div>
                </div>
                <div className="bg-yellow-50 p-4 rounded-xl text-center">
                  <div className="text-yellow-600 mb-1">{wishlist.pending}</div>
                  <div className="text-gray-500 text-sm">Pendentes</div>
                </div>
                <div className="bg-blue-50 p-4 rounded-xl text-center">
                  <div className="text-blue-600 mb-1">{wishlist.inProgress}</div>
                  <div className="text-gray-500 text-sm">Em Andamento</div>
                </div>
                <div className="bg-green-50 p-4 rounded-xl text-center">
                  <div className="text-green-600 mb-1">{wishlist.completed}</div>
                  <div className="text-gray-500 text-sm">Concluídos</div>
                </div>
              </div>
            </div>
          ))}
        </div>

        {/* Empty State (if no wishlists) */}
        {wishlists.length === 0 && (
          <div className="bg-white rounded-3xl shadow-lg p-12 text-center">
            <div className="bg-gray-100 w-20 h-20 rounded-full flex items-center justify-center mx-auto mb-4">
              <Heart className="w-10 h-10 text-gray-400" />
            </div>
            <h3 className="text-gray-800 mb-2">Nenhuma lista encontrada</h3>
            <p className="text-gray-500">Os idosos ainda não criaram listas de desejos.</p>
          </div>
        )}
      </div>
    </div>
  );
}
