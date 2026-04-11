// Description: Java 25 Table Object implementation for TableTweak.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamTableTweakTableObj
	implements ICFBamTableTweakTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamTableTweak.CLASS_CODE;
	protected static final int backingClassCode = ICFBamTableTweak.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamTableTweakObj> members;
	private Map<CFLibDbKeyHash256, ICFBamTableTweakObj> allTableTweak;
	private Map< ICFBamTweakByUNameIdxKey,
		ICFBamTableTweakObj > indexByUNameIdx;
	private Map< ICFBamTweakByValTentIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableTweakObj > > indexByValTentIdx;
	private Map< ICFBamTweakByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableTweakObj > > indexByScopeIdx;
	private Map< ICFBamTweakByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableTweakObj > > indexByDefSchemaIdx;
	private Map< ICFBamTweakByUDefIdxKey,
		ICFBamTableTweakObj > indexByUDefIdx;
	private Map< ICFBamTableTweakByTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTableTweakObj > > indexByTableIdx;
	public static String TABLE_NAME = "TableTweak";
	public static String TABLE_DBNAME = "tbltwk";

	public CFBamTableTweakTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamTableTweakObj>();
		allTableTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		indexByTableIdx = null;
	}

	public CFBamTableTweakTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamTableTweakObj>();
		allTableTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		indexByTableIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamTableTweakTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamTableTweakTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allTableTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		indexByTableIdx = null;
		List<ICFBamTableTweakObj> toForget = new LinkedList<ICFBamTableTweakObj>();
		ICFBamTableTweakObj cur = null;
		Iterator<ICFBamTableTweakObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamTableTweakObj.
	 */
	@Override
	public ICFBamTableTweakObj newInstance() {
		ICFBamTableTweakObj inst = new CFBamTableTweakObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamTableTweakObj.
	 */
	@Override
	public ICFBamTableTweakEditObj newEditInstance( ICFBamTableTweakObj orig ) {
		ICFBamTableTweakEditObj edit = new CFBamTableTweakEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamTableTweakObj realiseTableTweak( ICFBamTableTweakObj Obj ) {
		ICFBamTableTweakObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamTableTweakObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamTableTweakObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamTweakByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByValTentIdx != null ) {
				ICFBamTweakByValTentIdxKey keyValTentIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
				keyValTentIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					indexByValTentIdx.remove( keyValTentIdx );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamTweakByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
				keyUDefIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaTenantId( keepObj.getOptionalDefSchemaTenantId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.remove( keyUDefIdx );
			}

			if( indexByTableIdx != null ) {
				ICFBamTableTweakByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getFactoryTableTweak().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
				if( mapTableIdx != null ) {
					mapTableIdx.remove( keepObj.getPKey() );
					if( mapTableIdx.size() <= 0 ) {
						indexByTableIdx.remove( keyTableIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamTableTweakObj)schema.getTweakTableObj().realiseTweak( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamTweakByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByValTentIdx != null ) {
				ICFBamTweakByValTentIdxKey keyValTentIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
				keyValTentIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					mapValTentIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamTweakByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
				keyUDefIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaTenantId( keepObj.getOptionalDefSchemaTenantId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.put( keyUDefIdx, keepObj );
			}

			if( indexByTableIdx != null ) {
				ICFBamTableTweakByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getFactoryTableTweak().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
				if( mapTableIdx != null ) {
					mapTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allTableTweak != null ) {
				allTableTweak.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamTableTweakObj)schema.getTweakTableObj().realiseTweak( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allTableTweak != null ) {
				allTableTweak.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamTweakByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByValTentIdx != null ) {
				ICFBamTweakByValTentIdxKey keyValTentIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
				keyValTentIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					mapValTentIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamTweakByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
				keyUDefIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaTenantId( keepObj.getOptionalDefSchemaTenantId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.put( keyUDefIdx, keepObj );
			}

			if( indexByTableIdx != null ) {
				ICFBamTableTweakByTableIdxKey keyTableIdx =
					schema.getCFBamBackingStore().getFactoryTableTweak().newByTableIdxKey();
				keyTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamTableTweakObj > mapTableIdx = indexByTableIdx.get( keyTableIdx );
				if( mapTableIdx != null ) {
					mapTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamTableTweakObj createTableTweak( ICFBamTableTweakObj Obj ) {
		ICFBamTableTweakObj obj = Obj;
		ICFBamTableTweak rec = obj.getTableTweakRec();
		schema.getCFBamBackingStore().getTableTableTweak().createTableTweak(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamTableTweakObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamTableTweakObj readTableTweak( CFLibDbKeyHash256 pkey ) {
		return( readTableTweak( pkey, false ) );
	}

	@Override
	public ICFBamTableTweakObj readTableTweak( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamTableTweakObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamTableTweak readRec = schema.getCFBamBackingStore().getTableTableTweak().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamTableTweakObj)schema.getTweakTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamTableTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamTableTweakObj readCachedTableTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamTableTweakObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeTableTweak( ICFBamTableTweakObj obj )
	{
		final String S_ProcName = "CFBamTableTweakTableObj.reallyDeepDisposeTableTweak() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamTableTweakObj existing = readCachedTableTweak( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamTableTweakByTableIdxKey keyTableIdx = schema.getCFBamBackingStore().getFactoryTableTweak().newByTableIdxKey();
		keyTableIdx.setRequiredTableId( existing.getRequiredTableId() );



		if( indexByTableIdx != null ) {
			if( indexByTableIdx.containsKey( keyTableIdx ) ) {
				indexByTableIdx.get( keyTableIdx ).remove( pkey );
				if( indexByTableIdx.get( keyTableIdx ).size() <= 0 ) {
					indexByTableIdx.remove( keyTableIdx );
				}
			}
		}


		schema.getTweakTableObj().reallyDeepDisposeTweak( obj );
	}
	@Override
	public void deepDisposeTableTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamTableTweakObj obj = readCachedTableTweak( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamTableTweakObj lockTableTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamTableTweakObj locked = null;
		ICFBamTableTweak lockRec = schema.getCFBamBackingStore().getTableTableTweak().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamTableTweakObj)schema.getTweakTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamTableTweakObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockTableTweak", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamTableTweakObj> readAllTableTweak() {
		return( readAllTableTweak( false ) );
	}

	@Override
	public List<ICFBamTableTweakObj> readAllTableTweak( boolean forceRead ) {
		final String S_ProcName = "readAllTableTweak";
		if( ( allTableTweak == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamTableTweakObj> map = new HashMap<CFLibDbKeyHash256,ICFBamTableTweakObj>();
			allTableTweak = map;
			ICFBamTableTweak[] recList = schema.getCFBamBackingStore().getTableTableTweak().readAllDerived( null );
			ICFBamTableTweak rec;
			ICFBamTableTweakObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableTweakObj realised = (ICFBamTableTweakObj)obj.realise();
			}
		}
		int len = allTableTweak.size();
		ICFBamTableTweakObj arr[] = new ICFBamTableTweakObj[len];
		Iterator<ICFBamTableTweakObj> valIter = allTableTweak.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableTweakObj> arrayList = new ArrayList<ICFBamTableTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableTweakObj> cmp = new Comparator<ICFBamTableTweakObj>() {
			@Override
			public int compare( ICFBamTableTweakObj lhs, ICFBamTableTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTableTweakObj> readCachedAllTableTweak() {
		final String S_ProcName = "readCachedAllTableTweak";
		ArrayList<ICFBamTableTweakObj> arrayList = new ArrayList<ICFBamTableTweakObj>();
		if( allTableTweak != null ) {
			int len = allTableTweak.size();
			ICFBamTableTweakObj arr[] = new ICFBamTableTweakObj[len];
			Iterator<ICFBamTableTweakObj> valIter = allTableTweak.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamTableTweakObj> cmp = new Comparator<ICFBamTableTweakObj>() {
			public int compare( ICFBamTableTweakObj lhs, ICFBamTableTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamTableTweakObj readTableTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readTableTweakByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamTableTweakObj readTableTweakByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamTableTweakObj obj = readTableTweak( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamTableTweakObj readTableTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readTableTweakByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamTableTweakObj readTableTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamTweakByUNameIdxKey,
				ICFBamTableTweakObj >();
		}
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamTableTweakObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamTweak rec = schema.getCFBamBackingStore().getTableTweak().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamTableTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamTableTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamTableTweakObj> readTableTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readTableTweakByValTentIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamTableTweakObj> readTableTweakByValTentIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableTweakByValTentIdx";
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict;
		if( indexByValTentIdx == null ) {
			indexByValTentIdx = new HashMap< ICFBamTweakByValTentIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableTweakObj > >();
		}
		if( ( ! forceRead ) && indexByValTentIdx.containsKey( key ) ) {
			dict = indexByValTentIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByValTentIdx( null,
				TenantId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableTweakObj realised = (ICFBamTableTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByValTentIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableTweakObj arr[] = new ICFBamTableTweakObj[len];
		Iterator<ICFBamTableTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableTweakObj> arrayList = new ArrayList<ICFBamTableTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableTweakObj> cmp = new Comparator<ICFBamTableTweakObj>() {
			@Override
			public int compare( ICFBamTableTweakObj lhs, ICFBamTableTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTableTweakObj> readTableTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readTableTweakByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamTableTweakObj> readTableTweakByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableTweakByScopeIdx";
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamTweakByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableTweakObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableTweakObj realised = (ICFBamTableTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableTweakObj arr[] = new ICFBamTableTweakObj[len];
		Iterator<ICFBamTableTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableTweakObj> arrayList = new ArrayList<ICFBamTableTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableTweakObj> cmp = new Comparator<ICFBamTableTweakObj>() {
			@Override
			public int compare( ICFBamTableTweakObj lhs, ICFBamTableTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTableTweakObj> readTableTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readTableTweakByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamTableTweakObj> readTableTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableTweakByDefSchemaIdx";
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamTweakByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableTweakObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableTweakObj realised = (ICFBamTableTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableTweakObj arr[] = new ICFBamTableTweakObj[len];
		Iterator<ICFBamTableTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableTweakObj> arrayList = new ArrayList<ICFBamTableTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableTweakObj> cmp = new Comparator<ICFBamTableTweakObj>() {
			@Override
			public int compare( ICFBamTableTweakObj lhs, ICFBamTableTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamTableTweakObj readTableTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		return( readTableTweakByUDefIdx( TenantId,
			ScopeId,
			DefSchemaTenantId,
			DefSchemaId,
			Name,
			false ) );
	}

	@Override
	public ICFBamTableTweakObj readTableTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name, boolean forceRead )
	{
		if( indexByUDefIdx == null ) {
			indexByUDefIdx = new HashMap< ICFBamTweakByUDefIdxKey,
				ICFBamTableTweakObj >();
		}
		ICFBamTweakByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaTenantId( DefSchemaTenantId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		ICFBamTableTweakObj obj = null;
		if( ( ! forceRead ) && indexByUDefIdx.containsKey( key ) ) {
			obj = indexByUDefIdx.get( key );
		}
		else {
			ICFBamTweak rec = schema.getCFBamBackingStore().getTableTweak().readDerivedByUDefIdx( null,
				TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
			if( rec != null ) {
				obj = (ICFBamTableTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamTableTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamTableTweakObj> readTableTweakByTableIdx( CFLibDbKeyHash256 TableId )
	{
		return( readTableTweakByTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamTableTweakObj> readTableTweakByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readTableTweakByTableIdx";
		ICFBamTableTweakByTableIdxKey key = schema.getCFBamBackingStore().getFactoryTableTweak().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict;
		if( indexByTableIdx == null ) {
			indexByTableIdx = new HashMap< ICFBamTableTweakByTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableTweakObj > >();
		}
		if( ( ! forceRead ) && indexByTableIdx.containsKey( key ) ) {
			dict = indexByTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTableTweakObj>();
			ICFBamTableTweakObj obj;
			ICFBamTableTweak[] recList = schema.getCFBamBackingStore().getTableTableTweak().readDerivedByTableIdx( null,
				TableId );
			ICFBamTableTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTableTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTableTweakObj realised = (ICFBamTableTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTableTweakObj arr[] = new ICFBamTableTweakObj[len];
		Iterator<ICFBamTableTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTableTweakObj> arrayList = new ArrayList<ICFBamTableTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTableTweakObj> cmp = new Comparator<ICFBamTableTweakObj>() {
			@Override
			public int compare( ICFBamTableTweakObj lhs, ICFBamTableTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTableTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamTableTweakObj readCachedTableTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTableTweakObj obj = null;
		obj = readCachedTableTweak( Id );
		return( obj );
	}

	@Override
	public ICFBamTableTweakObj readCachedTableTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamTableTweakObj obj = null;
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamTableTweakObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamTableTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamTableTweakObj> readCachedTableTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedTableTweakByValTentIdx";
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamTableTweakObj> arrayList = new ArrayList<ICFBamTableTweakObj>();
		if( indexByValTentIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict;
			if( indexByValTentIdx.containsKey( key ) ) {
				dict = indexByValTentIdx.get( key );
				int len = dict.size();
				ICFBamTableTweakObj arr[] = new ICFBamTableTweakObj[len];
				Iterator<ICFBamTableTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableTweakObj obj;
			Iterator<ICFBamTableTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableTweakObj> cmp = new Comparator<ICFBamTableTweakObj>() {
			@Override
			public int compare( ICFBamTableTweakObj lhs, ICFBamTableTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamTableTweakObj> readCachedTableTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedTableTweakByScopeIdx";
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamTableTweakObj> arrayList = new ArrayList<ICFBamTableTweakObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamTableTweakObj arr[] = new ICFBamTableTweakObj[len];
				Iterator<ICFBamTableTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableTweakObj obj;
			Iterator<ICFBamTableTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableTweakObj> cmp = new Comparator<ICFBamTableTweakObj>() {
			@Override
			public int compare( ICFBamTableTweakObj lhs, ICFBamTableTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamTableTweakObj> readCachedTableTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedTableTweakByDefSchemaIdx";
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamTableTweakObj> arrayList = new ArrayList<ICFBamTableTweakObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamTableTweakObj arr[] = new ICFBamTableTweakObj[len];
				Iterator<ICFBamTableTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableTweakObj obj;
			Iterator<ICFBamTableTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableTweakObj> cmp = new Comparator<ICFBamTableTweakObj>() {
			@Override
			public int compare( ICFBamTableTweakObj lhs, ICFBamTableTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamTableTweakObj readCachedTableTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		ICFBamTableTweakObj obj = null;
		ICFBamTweakByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaTenantId( DefSchemaTenantId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		if( indexByUDefIdx != null ) {
			if( indexByUDefIdx.containsKey( key ) ) {
				obj = indexByUDefIdx.get( key );
			}
			else {
				Iterator<ICFBamTableTweakObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamTableTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamTableTweakObj> readCachedTableTweakByTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedTableTweakByTableIdx";
		ICFBamTableTweakByTableIdxKey key = schema.getCFBamBackingStore().getFactoryTableTweak().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamTableTweakObj> arrayList = new ArrayList<ICFBamTableTweakObj>();
		if( indexByTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict;
			if( indexByTableIdx.containsKey( key ) ) {
				dict = indexByTableIdx.get( key );
				int len = dict.size();
				ICFBamTableTweakObj arr[] = new ICFBamTableTweakObj[len];
				Iterator<ICFBamTableTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTableTweakObj obj;
			Iterator<ICFBamTableTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTableTweakObj> cmp = new Comparator<ICFBamTableTweakObj>() {
			@Override
			public int compare( ICFBamTableTweakObj lhs, ICFBamTableTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeTableTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTableTweakObj obj = readCachedTableTweakByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTableTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamTableTweakObj obj = readCachedTableTweakByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTableTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeTableTweakByValTentIdx";
		ICFBamTableTweakObj obj;
		List<ICFBamTableTweakObj> arrayList = readCachedTableTweakByValTentIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamTableTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeTableTweakByScopeIdx";
		ICFBamTableTweakObj obj;
		List<ICFBamTableTweakObj> arrayList = readCachedTableTweakByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamTableTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeTableTweakByDefSchemaIdx";
		ICFBamTableTweakObj obj;
		List<ICFBamTableTweakObj> arrayList = readCachedTableTweakByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamTableTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		ICFBamTableTweakObj obj = readCachedTableTweakByUDefIdx( TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTableTweakByTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeTableTweakByTableIdx";
		ICFBamTableTweakObj obj;
		List<ICFBamTableTweakObj> arrayList = readCachedTableTweakByTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamTableTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamTableTweakObj updateTableTweak( ICFBamTableTweakObj Obj ) {
		ICFBamTableTweakObj obj = Obj;
		schema.getCFBamBackingStore().getTableTableTweak().updateTableTweak( null,
			Obj.getTableTweakRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getTableTweakTableObj().getClassCode() ) {
			obj = (ICFBamTableTweakObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteTableTweak( ICFBamTableTweakObj Obj ) {
		ICFBamTableTweakObj obj = Obj;
		schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweak( null,
			obj.getTableTweakRec() );
		Obj.forget();
	}

	@Override
	public void deleteTableTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTableTweakObj obj = readTableTweak(Id);
		if( obj != null ) {
			ICFBamTableTweakEditObj editObj = (ICFBamTableTweakEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamTableTweakEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeTableTweakByIdIdx( Id );
	}

	@Override
	public void deleteTableTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamTweakByUNameIdxKey,
				ICFBamTableTweakObj >();
		}
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamTableTweakObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeTableTweakByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteTableTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByValTentIdx == null ) {
			indexByValTentIdx = new HashMap< ICFBamTweakByValTentIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableTweakObj > >();
		}
		if( indexByValTentIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict = indexByValTentIdx.get( key );
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByValTentIdx( null,
				TenantId );
			Iterator<ICFBamTableTweakObj> iter = dict.values().iterator();
			ICFBamTableTweakObj obj;
			List<ICFBamTableTweakObj> toForget = new LinkedList<ICFBamTableTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByValTentIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByValTentIdx( null,
				TenantId );
		}
		deepDisposeTableTweakByValTentIdx( TenantId );
	}

	@Override
	public void deleteTableTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamTweakByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableTweakObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamTableTweakObj> iter = dict.values().iterator();
			ICFBamTableTweakObj obj;
			List<ICFBamTableTweakObj> toForget = new LinkedList<ICFBamTableTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByScopeIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByScopeIdx( null,
				ScopeId );
		}
		deepDisposeTableTweakByScopeIdx( ScopeId );
	}

	@Override
	public void deleteTableTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamTweakByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableTweakObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamTableTweakObj> iter = dict.values().iterator();
			ICFBamTableTweakObj obj;
			List<ICFBamTableTweakObj> toForget = new LinkedList<ICFBamTableTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeTableTweakByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteTableTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		if( indexByUDefIdx == null ) {
			indexByUDefIdx = new HashMap< ICFBamTweakByUDefIdxKey,
				ICFBamTableTweakObj >();
		}
		ICFBamTweakByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaTenantId( DefSchemaTenantId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		ICFBamTableTweakObj obj = null;
		if( indexByUDefIdx.containsKey( key ) ) {
			obj = indexByUDefIdx.get( key );
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByUDefIdx( null,
				TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByUDefIdx( null,
				TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
		}
		deepDisposeTableTweakByUDefIdx( TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
	}

	@Override
	public void deleteTableTweakByTableIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamTableTweakByTableIdxKey key = schema.getCFBamBackingStore().getFactoryTableTweak().newByTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByTableIdx == null ) {
			indexByTableIdx = new HashMap< ICFBamTableTweakByTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTableTweakObj > >();
		}
		if( indexByTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTableTweakObj> dict = indexByTableIdx.get( key );
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByTableIdx( null,
				TableId );
			Iterator<ICFBamTableTweakObj> iter = dict.values().iterator();
			ICFBamTableTweakObj obj;
			List<ICFBamTableTweakObj> toForget = new LinkedList<ICFBamTableTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTableIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTableTweak().deleteTableTweakByTableIdx( null,
				TableId );
		}
		deepDisposeTableTweakByTableIdx( TableId );
	}
}