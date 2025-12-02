import { User, Shield, LogOut } from 'lucide-react';

interface HomeAdminProps {
  onNavigate: (screen: string) => void;
}

export function HomeAdmin({ onNavigate }: HomeAdminProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="flex items-center justify-between mb-8">
          <div className="flex items-center gap-4">
            <div className="bg-gradient-to-br from-green-200 to-blue-200 p-3 rounded-full">
              <Shield className="w-8 h-8 text-green-600" />
            </div>
            <div>
              <h1 className="text-gray-800">Painel Administrativo</h1>
              <p className="text-gray-500">Gerencie o sistema</p>
            </div>
          </div>
          <button 
            onClick={() => onNavigate('login')}
            className="p-3 hover:bg-white rounded-xl transition-colors"
          >
            <LogOut className="w-6 h-6 text-gray-600" />
          </button>
        </div>

        {/* Menu Cards */}
        <div className="grid md:grid-cols-2 gap-6">
          {/* Ver Perfil */}
          <button 
            onClick={() => onNavigate('perfil')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-blue-100 to-blue-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-blue-200 group-hover:to-blue-300 transition-all">
              <User className="w-10 h-10 text-blue-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Meu Perfil</h2>
            <p className="text-gray-500">Visualize e edite suas informações</p>
          </button>

          {/* Gerenciar Usuários */}
          <button 
            onClick={() => onNavigate('gerenciar-usuarios')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-green-100 to-green-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-green-200 group-hover:to-green-300 transition-all">
              <Shield className="w-10 h-10 text-green-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Gerenciar Usuários</h2>
            <p className="text-gray-500">Administre todos os usuários do sistema</p>
          </button>
        </div>

        {/* Dashboard Stats */}
        <div className="mt-8 bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-800 mb-4">Estatísticas do Sistema</h3>
          <div className="grid grid-cols-2 md:grid-cols-3 gap-4">
            <div className="bg-blue-50 p-4 rounded-xl">
              <div className="text-blue-600 mb-1">45</div>
              <div className="text-gray-600 text-sm">Total de Usuários</div>
            </div>
            <div className="bg-purple-50 p-4 rounded-xl">
              <div className="text-purple-600 mb-1">12</div>
              <div className="text-gray-600 text-sm">Idosos</div>
            </div>
            <div className="bg-green-50 p-4 rounded-xl">
              <div className="text-green-600 mb-1">33</div>
              <div className="text-gray-600 text-sm">Familiares</div>
            </div>
          </div>
        </div>

        {/* Recent Activity */}
        <div className="mt-6 bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-800 mb-4">Atividades Recentes</h3>
          <div className="space-y-3">
            <div className="flex items-center gap-3 p-4 bg-blue-50 rounded-xl">
              <div className="w-2 h-2 bg-blue-400 rounded-full"></div>
              <div className="flex-1">
                <p className="text-gray-700">Novo usuário cadastrado</p>
                <p className="text-gray-500 text-sm">Maria Santos - Idoso</p>
              </div>
              <span className="text-gray-400 text-sm">5 min atrás</span>
            </div>
            <div className="flex items-center gap-3 p-4 bg-purple-50 rounded-xl">
              <div className="w-2 h-2 bg-purple-400 rounded-full"></div>
              <div className="flex-1">
                <p className="text-gray-700">Perfil atualizado</p>
                <p className="text-gray-500 text-sm">João Silva - Familiar</p>
              </div>
              <span className="text-gray-400 text-sm">1 hora atrás</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
