import { ArrowLeft, Heart, Calendar, Package, ExternalLink, Plus } from 'lucide-react';

interface ViewWishlistScreenProps {
  onNavigate: (screen: string) => void;
}

export function ViewWishlistScreen({ onNavigate }: ViewWishlistScreenProps) {
  const items = [
    {
      id: 1,
      name: 'Cadeira de Balanço',
      description: 'Cadeira confortável para relaxar na varanda',
      quantity: 1,
      store: 'Loja de Móveis Casa & Lar',
      link: 'https://exemplo.com/cadeira',
      history: [
        { date: '02/12/2024', status: 'Em andamento' },
        { date: '20/11/2024', status: 'Pendente' },
      ],
    },
    {
      id: 2,
      name: 'Livro de Receitas',
      description: 'Livro com receitas tradicionais brasileiras',
      quantity: 1,
      store: 'Livraria Central',
      link: 'https://exemplo.com/livro',
      history: [
        { date: '01/12/2024', status: 'Concluído' },
        { date: '25/11/2024', status: 'Em andamento' },
        { date: '15/11/2024', status: 'Pendente' },
      ],
    },
    {
      id: 3,
      name: 'Kit de Jardinagem',
      description: 'Ferramentas para cuidar do jardim',
      quantity: 1,
      store: 'Jardim & Cia',
      link: 'https://exemplo.com/jardinagem',
      history: [
        { date: '15/11/2024', status: 'Pendente' },
      ],
    },
  ];

  const getStatusColor = (status: string) => {
    if (status === 'Pendente') return 'bg-yellow-100 text-yellow-700 border-yellow-200';
    if (status === 'Em andamento') return 'bg-blue-100 text-blue-700 border-blue-200';
    if (status === 'Concluído') return 'bg-green-100 text-green-700 border-green-200';
    return 'bg-gray-100 text-gray-700 border-gray-200';
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-5xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('opcoes-lista')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <h1 className="text-gray-800">Minha Lista de Desejos</h1>
        </div>

        {/* List Header Card */}
        <div className="bg-white rounded-3xl shadow-xl p-8 mb-6">
          <div className="flex items-start justify-between mb-6">
            <div className="flex items-center gap-4">
              <div className="bg-gradient-to-br from-purple-200 to-blue-200 p-4 rounded-2xl">
                <Heart className="w-10 h-10 text-purple-600" />
              </div>
              <div>
                <h2 className="text-gray-800">Aniversário de 80 Anos</h2>
                <div className="flex items-center gap-2 text-gray-500 mt-1">
                  <Calendar className="w-4 h-4" />
                  <span className="text-sm">Criada em 15/11/2024</span>
                  <span className="mx-2">•</span>
                  <span className="text-sm">ID: #LST-001</span>
                </div>
              </div>
            </div>
            <div className="flex gap-3">
              <button 
                onClick={() => onNavigate('itens-lista')}
                className="bg-gradient-to-r from-purple-300 to-blue-300 text-gray-800 px-6 py-3 rounded-xl hover:from-purple-400 hover:to-blue-400 transition-all shadow-md hover:shadow-lg flex items-center gap-2"
              >
                <Plus className="w-5 h-5" />
                Adicionar Item
              </button>
            </div>
          </div>

          {/* Stats */}
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
            <div className="bg-gray-50 p-4 rounded-xl text-center">
              <div className="text-gray-800 mb-1">{items.length}</div>
              <div className="text-gray-500 text-sm">Total de Itens</div>
            </div>
            <div className="bg-yellow-50 p-4 rounded-xl text-center">
              <div className="text-yellow-600 mb-1">1</div>
              <div className="text-gray-500 text-sm">Pendentes</div>
            </div>
            <div className="bg-blue-50 p-4 rounded-xl text-center">
              <div className="text-blue-600 mb-1">1</div>
              <div className="text-gray-500 text-sm">Em Andamento</div>
            </div>
            <div className="bg-green-50 p-4 rounded-xl text-center">
              <div className="text-green-600 mb-1">1</div>
              <div className="text-gray-500 text-sm">Concluídos</div>
            </div>
          </div>
        </div>

        {/* Items List */}
        <div className="space-y-4">
          {items.map((item) => (
            <div key={item.id} className="bg-white rounded-3xl shadow-lg p-6 hover:shadow-xl transition-all">
              <div className="flex flex-col lg:flex-row gap-6">
                {/* Item Info */}
                <div className="flex-1">
                  <div className="flex items-start justify-between mb-3">
                    <div>
                      <div className="flex items-center gap-3 mb-2">
                        <h3 className="text-gray-800">{item.name}</h3>
                        <span className="text-gray-400 text-sm">#{item.id.toString().padStart(3, '0')}</span>
                      </div>
                      <p className="text-gray-500">{item.description}</p>
                    </div>
                  </div>

                  <div className="grid grid-cols-1 md:grid-cols-2 gap-3 mb-4">
                    <div className="flex items-center gap-2 text-sm">
                      <Package className="w-4 h-4 text-gray-400" />
                      <span className="text-gray-600">Quantidade: {item.quantity}</span>
                    </div>
                    <div className="flex items-center gap-2 text-sm">
                      <span className="text-gray-600">Loja: {item.store}</span>
                    </div>
                  </div>

                  {item.link && (
                    <a 
                      href={item.link} 
                      target="_blank" 
                      rel="noopener noreferrer"
                      className="inline-flex items-center gap-2 text-purple-600 hover:text-purple-700 text-sm"
                    >
                      <ExternalLink className="w-4 h-4" />
                      Ver produto na loja
                    </a>
                  )}
                </div>

                {/* History Timeline */}
                <div className="lg:w-72 bg-gradient-to-br from-gray-50 to-purple-50 rounded-2xl p-4">
                  <h4 className="text-gray-700 mb-3 flex items-center gap-2">
                    <Calendar className="w-4 h-4" />
                    Histórico
                  </h4>
                  <div className="space-y-3">
                    {item.history.map((entry, index) => (
                      <div key={index} className="flex items-start gap-3">
                        <div className="relative">
                          <div className={`w-3 h-3 rounded-full border-2 ${getStatusColor(entry.status).replace('bg-', 'border-').replace('text-', 'bg-')}`}></div>
                          {index < item.history.length - 1 && (
                            <div className="absolute left-1/2 top-3 w-0.5 h-6 bg-gray-200 -translate-x-1/2"></div>
                          )}
                        </div>
                        <div className="flex-1">
                          <div className={`inline-block px-3 py-1 rounded-full text-xs border-2 ${getStatusColor(entry.status)} mb-1`}>
                            {entry.status}
                          </div>
                          <div className="text-gray-500 text-xs">{entry.date}</div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              </div>

              {/* Action Buttons - Full CRUD for Elder */}
              <div className="flex gap-3 mt-4 pt-4 border-t border-gray-100">
                <button 
                  onClick={() => onNavigate('editar-item')}
                  className="flex-1 bg-blue-50 text-blue-600 py-2 rounded-lg hover:bg-blue-100 transition-colors text-sm"
                >
                  Editar Item
                </button>
                <button 
                  onClick={() => onNavigate('status-item')}
                  className="flex-1 bg-purple-50 text-purple-600 py-2 rounded-lg hover:bg-purple-100 transition-colors text-sm"
                >
                  Ver Status
                </button>
                <button className="flex-1 bg-red-50 text-red-600 py-2 rounded-lg hover:bg-red-100 transition-colors text-sm">
                  Excluir Item
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
