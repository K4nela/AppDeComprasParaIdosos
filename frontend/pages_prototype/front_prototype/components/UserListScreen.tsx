import { ArrowLeft, Search, User, Shield, Heart } from 'lucide-react';

interface UserListScreenProps {
  onNavigate: (screen: string) => void;
}

export function UserListScreen({ onNavigate }: UserListScreenProps) {
  const users = [
    { id: 1, name: 'Maria Santos', type: 'Idoso', email: 'maria@email.com', phone: '(11) 91234-5678', birth: '12/05/1945' },
    { id: 2, name: 'João Silva', type: 'Familiar', email: 'joao@email.com', phone: '(11) 98765-4321', birth: '20/08/1980' },
    { id: 3, name: 'Pedro Admin', type: 'Administrador', email: 'pedro@email.com', phone: '(11) 99999-9999', birth: '15/03/1975' },
    { id: 4, name: 'Ana Costa', type: 'Idoso', email: 'ana@email.com', phone: '(11) 92222-3333', birth: '30/11/1950' },
  ];

  const getTypeColor = (type: string) => {
    if (type === 'Idoso') return 'bg-blue-100 text-blue-600';
    if (type === 'Familiar') return 'bg-purple-100 text-purple-600';
    return 'bg-green-100 text-green-600';
  };

  const getTypeIcon = (type: string) => {
    if (type === 'Idoso') return <Heart className="w-4 h-4" />;
    if (type === 'Familiar') return <User className="w-4 h-4" />;
    return <Shield className="w-4 h-4" />;
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-6xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('gerenciar-usuarios')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <h1 className="text-gray-800">Lista de Usuários</h1>
        </div>

        {/* Search and Filter */}
        <div className="bg-white rounded-3xl shadow-lg p-6 mb-6">
          <div className="flex flex-col md:flex-row gap-4">
            <div className="flex-1 relative">
              <Search className="absolute left-4 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
              <input
                type="text"
                placeholder="Buscar por nome, email ou ID..."
                className="w-full pl-12 pr-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>
            <div className="md:w-48">
              <select className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors">
                <option>Todos os tipos</option>
                <option>Idoso</option>
                <option>Familiar</option>
                <option>Administrador</option>
              </select>
            </div>
          </div>
        </div>

        {/* User Cards */}
        <div className="space-y-4">
          {users.map((user) => (
            <div 
              key={user.id} 
              onClick={() => onNavigate('detalhe-usuario')}
              className="bg-white rounded-3xl shadow-lg p-6 hover:shadow-xl transition-all cursor-pointer"
            >
              <div className="flex flex-col md:flex-row gap-6">
                {/* Avatar */}
                <div className="flex items-center gap-4">
                  <div className="bg-gradient-to-br from-blue-200 to-purple-200 p-4 rounded-2xl">
                    <User className="w-8 h-8 text-purple-600" />
                  </div>
                  <div className="md:hidden">
                    <h3 className="text-gray-800">{user.name}</h3>
                    <div className={`flex items-center gap-2 px-3 py-1 rounded-full text-sm mt-1 ${getTypeColor(user.type)} inline-flex`}>
                      {getTypeIcon(user.type)}
                      <span>{user.type}</span>
                    </div>
                  </div>
                </div>

                {/* Info Grid */}
                <div className="flex-1 grid grid-cols-1 md:grid-cols-5 gap-4">
                  <div className="hidden md:block">
                    <div className="text-gray-500 text-sm mb-1">ID</div>
                    <div className="text-gray-800">#{user.id.toString().padStart(5, '0')}</div>
                  </div>
                  <div className="hidden md:block">
                    <div className="text-gray-500 text-sm mb-1">Tipo</div>
                    <div className={`flex items-center gap-2 px-3 py-1 rounded-full text-sm ${getTypeColor(user.type)} inline-flex`}>
                      {getTypeIcon(user.type)}
                      <span>{user.type}</span>
                    </div>
                  </div>
                  <div className="hidden md:block">
                    <div className="text-gray-500 text-sm mb-1">Nome</div>
                    <div className="text-gray-800">{user.name}</div>
                  </div>
                  <div>
                    <div className="text-gray-500 text-sm mb-1">Email</div>
                    <div className="text-gray-800">{user.email}</div>
                  </div>
                  <div>
                    <div className="text-gray-500 text-sm mb-1">Telefone</div>
                    <div className="text-gray-800">{user.phone}</div>
                  </div>
                </div>
              </div>

              {/* Mobile Additional Info */}
              <div className="md:hidden mt-4 pt-4 border-t border-gray-100 grid grid-cols-2 gap-4">
                <div>
                  <div className="text-gray-500 text-sm mb-1">ID</div>
                  <div className="text-gray-800">#{user.id.toString().padStart(5, '0')}</div>
                </div>
                <div>
                  <div className="text-gray-500 text-sm mb-1">Data Nasc.</div>
                  <div className="text-gray-800">{user.birth}</div>
                </div>
              </div>
            </div>
          ))}
        </div>

        {/* Pagination */}
        <div className="flex justify-center gap-2 mt-6">
          <button className="px-4 py-2 bg-white rounded-xl border-2 border-gray-200 text-gray-600 hover:border-blue-300 transition-colors">
            Anterior
          </button>
          <button className="px-4 py-2 bg-gradient-to-r from-blue-300 to-purple-300 rounded-xl text-gray-800">
            1
          </button>
          <button className="px-4 py-2 bg-white rounded-xl border-2 border-gray-200 text-gray-600 hover:border-blue-300 transition-colors">
            2
          </button>
          <button className="px-4 py-2 bg-white rounded-xl border-2 border-gray-200 text-gray-600 hover:border-blue-300 transition-colors">
            Próxima
          </button>
        </div>
      </div>
    </div>
  );
}
