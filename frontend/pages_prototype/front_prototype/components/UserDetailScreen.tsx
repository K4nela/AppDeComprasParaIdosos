import { ArrowLeft, User, Mail, Phone, MapPin, Calendar, Shield, Edit } from 'lucide-react';

interface UserDetailScreenProps {
  onNavigate: (screen: string) => void;
}

export function UserDetailScreen({ onNavigate }: UserDetailScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-3xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('lista-usuarios')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <h1 className="text-gray-800">Detalhes do Usuário</h1>
        </div>

        {/* Detail Card */}
        <div className="bg-white rounded-3xl shadow-xl p-8">
          {/* Avatar Section */}
          <div className="flex flex-col items-center mb-8">
            <div className="bg-gradient-to-br from-blue-200 to-purple-200 p-6 rounded-full mb-4">
              <User className="w-16 h-16 text-purple-600" />
            </div>
            <h2 className="text-gray-800">Maria Santos</h2>
            <div className="flex items-center gap-2 mt-2 px-4 py-2 bg-blue-100 rounded-full">
              <Shield className="w-4 h-4 text-blue-600" />
              <span className="text-blue-600 text-sm">Idoso</span>
            </div>
          </div>

          {/* Info Grid */}
          <div className="space-y-4 mb-8">
            <div className="flex items-start gap-4 p-4 bg-gray-50 rounded-xl">
              <div className="bg-blue-100 p-2 rounded-lg">
                <User className="w-5 h-5 text-blue-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-500 text-sm mb-1">ID do Usuário</div>
                <div className="text-gray-800">#USR-00001</div>
              </div>
            </div>

            <div className="flex items-start gap-4 p-4 bg-gray-50 rounded-xl">
              <div className="bg-purple-100 p-2 rounded-lg">
                <Calendar className="w-5 h-5 text-purple-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-500 text-sm mb-1">Data de Nascimento</div>
                <div className="text-gray-800">12/05/1945 (79 anos)</div>
              </div>
            </div>

            <div className="flex items-start gap-4 p-4 bg-gray-50 rounded-xl">
              <div className="bg-green-100 p-2 rounded-lg">
                <Mail className="w-5 h-5 text-green-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-500 text-sm mb-1">Email</div>
                <div className="text-gray-800">maria.santos@email.com</div>
              </div>
            </div>

            <div className="flex items-start gap-4 p-4 bg-gray-50 rounded-xl">
              <div className="bg-yellow-100 p-2 rounded-lg">
                <Phone className="w-5 h-5 text-yellow-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-500 text-sm mb-1">Telefone</div>
                <div className="text-gray-800">(11) 91234-5678</div>
              </div>
            </div>

            <div className="flex items-start gap-4 p-4 bg-gray-50 rounded-xl">
              <div className="bg-pink-100 p-2 rounded-lg">
                <MapPin className="w-5 h-5 text-pink-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-500 text-sm mb-1">Endereço</div>
                <div className="text-gray-800">Av. Principal, 456 - Jardim das Acácias, São Paulo - SP, CEP 01234-567</div>
              </div>
            </div>
          </div>

          {/* Additional Info */}
          <div className="bg-gradient-to-r from-blue-50 to-purple-50 rounded-2xl p-6 mb-8">
            <h3 className="text-gray-800 mb-3">Informações Adicionais</h3>
            <div className="grid grid-cols-2 gap-4 text-sm">
              <div>
                <span className="text-gray-500">Data de Cadastro:</span>
                <div className="text-gray-800">01/01/2024</div>
              </div>
              <div>
                <span className="text-gray-500">Último Acesso:</span>
                <div className="text-gray-800">02/12/2024 14:30</div>
              </div>
              <div>
                <span className="text-gray-500">Status:</span>
                <div className="text-green-600">Ativo</div>
              </div>
              <div>
                <span className="text-gray-500">Listas Criadas:</span>
                <div className="text-gray-800">3</div>
              </div>
            </div>
          </div>

          {/* Action Buttons */}
          <div className="flex gap-3">
            <button 
              onClick={() => onNavigate('lista-usuarios')}
              className="flex-1 bg-white border-2 border-gray-200 text-gray-700 py-4 rounded-xl hover:bg-gray-50 transition-all"
            >
              Voltar
            </button>
            <button className="flex-1 bg-gradient-to-r from-blue-300 to-purple-300 text-gray-800 py-4 rounded-xl hover:from-blue-400 hover:to-purple-400 transition-all shadow-md hover:shadow-lg flex items-center justify-center gap-2">
              <Edit className="w-5 h-5" />
              Editar Usuário
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
