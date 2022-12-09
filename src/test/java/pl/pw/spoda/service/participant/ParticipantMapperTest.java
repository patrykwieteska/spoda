package pl.pw.spoda.service.participant;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import pl.pw.spoda.database.entities.Participant;
import pl.pw.spoda.dto.ParticipantDto;
import pl.pw.spoda.service.DateService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ParticipantMapperTest {

    private static final String CREATED_BY = "createdBy_";
    private static final String NAME = "name_";
    private static final String DISPLAY_NAME = "displayName_";
    private static final LocalDateTime CREATION_DATE = LocalDateTime.of( 2022, 12, 10, 3, 54, 12 );
    private static final LocalDateTime LAST_MODIFICATION_DATE = LocalDateTime.of( 2022, 11, 13, 2, 33, 43 );

    @Mock
    private DateService dateService;
    @InjectMocks
    private ParticipantMapper participantMapper;

    @Test
    public void mapToResponseTest() {
        assertThat( participantMapper.mapToResponse( stubParticipant( 2 ) ) )
                .isEqualTo( ParticipantDto.builder()
                        .id( 2 )
                        .name( "name_2" )
                        .displayName( "displayName_2" )
                        .createdBy( "createdBy_2" )
                        .lastModificationDate( LAST_MODIFICATION_DATE )
                        .creationDate( CREATION_DATE )
                        .build() );
    }

    @Test
    public void mapToEntityTest() {
        Participant expectedParticipant = Participant.builder()
                .name( "name_" )
                .displayName( "displayName_" )
                .build();
        expectedParticipant.setCreatedBy( "createdBy_" );
        expectedParticipant.setLastModificationDate( LAST_MODIFICATION_DATE );

        when( dateService.getCurrentDate() ).thenReturn( LAST_MODIFICATION_DATE );

        assertThat( participantMapper.mapToEntity( stubParticipantRequest(  ) ) )
                .isEqualTo( expectedParticipant );
        verify( dateService, only() ).getCurrentDate();
    }

    @Test
    public void mapToParticipantList() {
        List<Participant> participantList = List.of(
                stubParticipant( 1 ),
                stubParticipant( 2 )
        );


        assertThat( participantMapper.mapToParticipantList( participantList ) ).containsExactlyInAnyOrder(
                ParticipantDto.builder()
                        .id( 2 )
                        .name( NAME+2 )
                        .displayName( DISPLAY_NAME+2 )
                        .createdBy( CREATED_BY+2 )
                        .creationDate( CREATION_DATE )
                        .lastModificationDate( LAST_MODIFICATION_DATE )
                        .build(),
                ParticipantDto.builder()
                        .id( 1)
                        .name( NAME+1 )
                        .displayName( DISPLAY_NAME+1 )
                        .createdBy( CREATED_BY+1 )
                        .creationDate( CREATION_DATE )
                        .lastModificationDate( LAST_MODIFICATION_DATE )
                        .build()
        );
    }

    private Participant stubParticipant(int number) {
        Participant participant = Participant.builder()
                .id( number )
                .name( NAME + number )
                .displayName( DISPLAY_NAME + number )
                .build();
        participant.setCreationDate( CREATION_DATE );
        participant.setLastModificationDate( LAST_MODIFICATION_DATE );
        participant.setCreatedBy( CREATED_BY + number );
        return participant;
    }

    private CreateParticipantRequest stubParticipantRequest() {
        return CreateParticipantRequest.builder()
                .name( NAME )
                .displayName( DISPLAY_NAME )
                .build();
    }
}