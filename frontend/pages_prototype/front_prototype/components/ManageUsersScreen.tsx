import { ArrowLeft, List, Edit, Trash2 } from 'lucide-react';

interface ManageUsersScreenProps {
  onNavigate: (screen: string) => void;
}

export function ManageUsersScreen({ onNavigate }: ManageUsersScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('home-admin')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <h1 className="text-gray-800">Gerenciar Usuários</h1>
        </div>

        {/* Action Cards */}
        <div className="grid md:grid-cols-3 gap-6">
          {/* Listar Usuários */}
          <button 
            onClick={() => onNavigate('lista-usuarios')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-blue-100 to-blue-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-blue-200 group-hover:to-blue-300 transition-all">
              <List className="w-10 h-10 text-blue-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Listar Usuários</h2>
            <p className="text-gray-500">Visualize todos os usuários cadastrados no sistema</p>
          </button>

          {/* Atualizar Usuário */}
          <button 
            onClick={() => onNavigate('lista-usuarios')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-purple-100 to-purple-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-purple-200 group-hover:to-purple-300 transition-all">
              <Edit className="w-10 h-10 text-purple-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Atualizar Usuário</h2>
            <p className="text-gray-500">Edite informações de usuários existentes</p>
          </button>

          {/* Deletar Usuário */}
          <button 
            onClick={() => onNavigate('lista-usuarios')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-red-100 to-red-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-red-200 group-hover:to-red-300 transition-all">
              <Trash2 className="w-10 h-10 text-red-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Deletar Usuário</h2>
            <p className="text-gray-500">Remova usuários do sistema permanentemente</p>
          </button>
        </div>

        {/* Quick Stats */}
        <div className="mt-8 bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-800 mb-4">Resumo de Usuários</h3>
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
            <div className="bg-blue-50 p-4 rounded-xl text-center">
              <div className="text-blue-600 mb-1">45</div>
              <div className="text-gray-600 text-sm">Total</div>
            </div>
            <div className="bg-purple-50 p-4 rounded-xl text-center">
              <div className="text-purple-600 mb-1">12</div>
              <div className="text-gray-600 text-sm">Idosos</div>
            </div>
            <div className="bg-green-50 p-4 rounded-xl text-center">
              <div className="text-green-600 mb-1">30</div>
              <div className="text-gray-600 text-sm">Familiares</div>
            </div>
            <div className="bg-yellow-50 p-4 rounded-xl text-center">
              <div className="text-yellow-600 mb-1">3</div>
              <div className="text-gray-600 text-sm">Admins</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
