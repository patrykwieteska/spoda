package pl.pw.spoda.service.participant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.pw.spoda.database.entities.Participant;
import pl.pw.spoda.dto.ParticipantDto;
import pl.pw.spoda.exceptions.ParticipantAlreadyExistsException;
import pl.pw.spoda.exceptions.ParticipantNotFoundException;
import pl.pw.spoda.repository.ParticipantRepository;
import pl.pw.spoda.service.DateService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ParticipantServiceTest {

    private static final String DISPLAY_NAME = "displayName";
    private static final String NAME = "name";
    @Mock
    private ParticipantRepository participantRepository;
    @Mock
    private ParticipantMapper participantMapper;
    @Mock
    private DateService dateService;
    @InjectMocks
    private ParticipantService participantService;


    @Test
    public void addParticipantTest() {
        CreateParticipantRequest request = stubParticipantRequest();
        Participant participant = new Participant();
        when( participantRepository.findByDisplayName( DISPLAY_NAME ) ).thenReturn( Optional.empty() );
        when( participantMapper.mapToEntity( request ) ).thenReturn( participant );

        participantService.addParticipant( request );

        verify( participantMapper, Mockito.times( 1 ) ).mapToEntity( request );
        verifyNoMoreInteractions( participantMapper );
    }

    @Test
    public void addParticipantTestThrowsException() {
        CreateParticipantRequest request = stubParticipantRequest();
        when( participantRepository.findByDisplayName( DISPLAY_NAME ) ).thenReturn( Optional.of( new Participant() ) );
        assertThrows( ParticipantAlreadyExistsException.class, () -> participantService.addParticipant( request ) );
    }

    @Test
    public void getParticipantByIdTest() {

        Participant participant = new Participant();
        ParticipantDto participantDto = new ParticipantDto();

        when( participantRepository.findById( 1 ) ).thenReturn( Optional.of( participant ) );
        when( participantMapper.mapToResponse( participant ) ).thenReturn( participantDto );

        assertThat( participantService.getParticipantById( 1 ) ).isEqualTo( participantDto );
        verify( participantMapper, only() ).mapToResponse( participant );
    }

    @Test
    public void getParticipantByIdTestThrowsException() {
        when( participantRepository.findById( 1 ) ).thenReturn( Optional.empty() );
        assertThrows( ParticipantNotFoundException.class, () -> participantService.getParticipantById( 1 ) );
    }

    private CreateParticipantRequest stubParticipantRequest() {
        return CreateParticipantRequest.builder()
                .name( NAME )
                .displayName( DISPLAY_NAME )
                .build();
    }

    @Test
    public void getParticipantsTest() {
        List<Participant> participantList = List.of( new Participant() );
        List<ParticipantDto> participantDtoList = List.of( new ParticipantDto() );

        when( participantMapper.mapToParticipantList( participantList ) ).thenReturn( participantDtoList );
        when( participantRepository.findAllParticipants() ).thenReturn( participantList );

        assertThat( participantService.getParticipants() ).isEqualTo( participantDtoList );
        verify( participantMapper, only() ).mapToParticipantList( participantList );
    }
}