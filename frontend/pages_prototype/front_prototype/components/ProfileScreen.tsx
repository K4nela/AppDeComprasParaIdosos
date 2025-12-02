import { ArrowLeft, User, Edit, Trash2, Mail, Phone, MapPin, Calendar, Shield } from 'lucide-react';

interface ProfileScreenProps {
  onNavigate: (screen: string) => void;
}

export function ProfileScreen({ onNavigate }: ProfileScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-3xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('home-familiar')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <h1 className="text-gray-800">Meu Perfil</h1>
        </div>

        {/* Profile Card */}
        <div className="bg-white rounded-3xl shadow-xl p-8">
          {/* Avatar Section */}
          <div className="flex flex-col items-center mb-8">
            <div className="bg-gradient-to-br from-blue-200 to-purple-200 p-6 rounded-full mb-4">
              <User className="w-16 h-16 text-purple-600" />
            </div>
            <h2 className="text-gray-800">Ana Maria Silva</h2>
            <div className="flex items-center gap-2 mt-2 px-4 py-2 bg-purple-100 rounded-full">
              <Shield className="w-4 h-4 text-purple-600" />
              <span className="text-purple-600 text-sm">Familiar</span>
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
                <div className="text-gray-800">#USR-00123</div>
              </div>
            </div>

            <div className="flex items-start gap-4 p-4 bg-gray-50 rounded-xl">
              <div className="bg-purple-100 p-2 rounded-lg">
                <Calendar className="w-5 h-5 text-purple-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-500 text-sm mb-1">Data de Nascimento</div>
                <div className="text-gray-800">15/03/1985</div>
              </div>
            </div>

            <div className="flex items-start gap-4 p-4 bg-gray-50 rounded-xl">
              <div className="bg-green-100 p-2 rounded-lg">
                <Mail className="w-5 h-5 text-green-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-500 text-sm mb-1">Email</div>
                <div className="text-gray-800">ana.silva@email.com</div>
              </div>
            </div>

            <div className="flex items-start gap-4 p-4 bg-gray-50 rounded-xl">
              <div className="bg-yellow-100 p-2 rounded-lg">
                <Phone className="w-5 h-5 text-yellow-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-500 text-sm mb-1">Telefone</div>
                <div className="text-gray-800">(11) 98765-4321</div>
              </div>
            </div>

            <div className="flex items-start gap-4 p-4 bg-gray-50 rounded-xl">
              <div className="bg-pink-100 p-2 rounded-lg">
                <MapPin className="w-5 h-5 text-pink-600" />
              </div>
              <div className="flex-1">
                <div className="text-gray-500 text-sm mb-1">Endereço</div>
                <div className="text-gray-800">Rua das Flores, 123 - Centro, São Paulo - SP</div>
              </div>
            </div>
          </div>

          {/* Action Buttons */}
          <div className="space-y-3">
            <button 
              onClick={() => onNavigate('atualizar-dados')}
              className="w-full bg-gradient-to-r from-blue-300 to-purple-300 text-gray-800 py-4 rounded-xl hover:from-blue-400 hover:to-purple-400 transition-all shadow-md hover:shadow-lg flex items-center justify-center gap-2"
            >
              <Edit className="w-5 h-5" />
              Atualizar Dados Pessoais
            </button>

            <button className="w-full bg-white border-2 border-red-200 text-red-600 py-4 rounded-xl hover:bg-red-50 transition-all flex items-center justify-center gap-2">
              <Trash2 className="w-5 h-5" />
              Deletar Perfil
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
